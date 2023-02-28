package hello.springcoreadvanced1.trace.hellotrace;

import hello.springcoreadvanced1.trace.TraceId;
import hello.springcoreadvanced1.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

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
     * 첫 번째 Trace
     *
     * @param message 로그 메시지
     * @return {@link TraceStatus}
     */
    public TraceStatus begin(String message) {
        return beginTrace(new TraceId(), message);
    }

    /**
     * V2 추가<br>
     * 두 번째 이후 Trace
     *
     * @param beforeTraceId 이전 Trace
     * @param message       로그 메시지
     * @return {@link TraceStatus}
     */
    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        return beginTrace(beforeTraceId.createNextId(), message);
    }

    /**
     * V2 추가<br>
     * {@link #begin}, {@link #beginSync} 에서 사용
     */
    private TraceStatus beginTrace(TraceId trace, String message) {
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", trace.getId(), addSpace(START_PREFIX, trace.getLevel()), message);

        return new TraceStatus(trace, startTimeMs, message);
    }

    /**
     * 현재 Trace 종료
     * - 일반 종료
     *
     * @param status {@link TraceStatus}
     */
    public void end(TraceStatus status) {
        complete(status, null);
    }

    /**
     * 현재 Trace 종료
     * - 예외 발생
     *
     * @param status {@link TraceStatus}
     * @param e      발생한 예외
     */
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
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
    }
}
