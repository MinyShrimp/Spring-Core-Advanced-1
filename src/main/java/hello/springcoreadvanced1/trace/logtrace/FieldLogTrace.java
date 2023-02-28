package hello.springcoreadvanced1.trace.logtrace;

import hello.springcoreadvanced1.trace.TraceId;
import hello.springcoreadvanced1.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    /**
     * traceId 동기화, 동시성 이슈 발생
     */
    private TraceId traceIdHolder;

    /**
     * Trace Level 에 따라 공간을 확보하는 메서드
     * <p>- LEVEL 0: </p>
     * <p>- LEVEL 1: |--></p>
     * <p>- LEVEL 2: |   |--></p>
     *
     * @param prefix 적용할 접두사
     * @param level  현재 Trace Level
     */
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }

    /**
     * TraceId 동기화<br>
     * - 최초 호출: 새로운 TraceId 생성<br>
     * - 이후 호출: 다음 Level TraceId 생성
     */
    private void syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = new TraceId();
        } else {
            traceIdHolder = traceIdHolder.createNextId();
        }
    }

    /**
     * TraceId LEVEL 감소<br>
     * - Level 0 : null<br>
     * - Level 1~: 이전 Level TraceId 생성
     */
    private void releaseTraceId() {
        if (traceIdHolder.isFirstLevel()) {
            traceIdHolder = null;
        } else {
            traceIdHolder = traceIdHolder.createPreviousId();
        }
    }

    /**
     * 현재 Trace가 종료됨에 따라 로그를 출력하기 위함
     *
     * @param status {@link TraceStatus}
     * @param e      발생한 예외
     */
    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time = {}ms",
                    traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                    status.getMessage(), resultTimeMs
            );
        } else {
            log.info("[{}] {}{} time = {}ms ex = {}",
                    traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()),
                    status.getMessage(), resultTimeMs, e.toString()
            );
        }

        releaseTraceId();
    }

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();

        TraceId traceId = traceIdHolder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }
}
