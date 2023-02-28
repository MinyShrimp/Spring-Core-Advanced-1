package hello.springcoreadvanced1.trace;

import lombok.Getter;

/**
 * 로그 상태 정보
 */
@Getter
public class TraceStatus {
    /**
     * 최초 Trace
     */
    private final TraceId traceId;

    /**
     * 시작 시간
     */
    private final Long startTimeMs;

    /**
     * 최근 로그 메시지
     */
    private final String message;

    /**
     * @param traceId     최초 Trace
     * @param startTimeMs 시작 시간
     * @param message     로그 메시지
     */
    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }
}
