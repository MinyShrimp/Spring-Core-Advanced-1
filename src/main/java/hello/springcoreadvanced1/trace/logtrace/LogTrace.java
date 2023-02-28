package hello.springcoreadvanced1.trace.logtrace;

import hello.springcoreadvanced1.trace.TraceStatus;

/**
 * LogTrace Interface
 */
public interface LogTrace {
    /**
     * 해당 객체가 시작할때 호출
     *
     * @param message 메시지
     * @return {@link TraceStatus}
     */
    TraceStatus begin(String message);

    /**
     * 정상 종료시 호출
     *
     * @param status {@link TraceStatus}
     */
    void end(TraceStatus status);

    /**
     * 예외 발생시 호출
     *
     * @param status {@link TraceStatus}
     * @param e      발생한 예외
     */
    void exception(TraceStatus status, Exception e);
}
