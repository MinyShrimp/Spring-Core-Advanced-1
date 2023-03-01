package hello.springcoreadvanced1.trace.callback;

import hello.springcoreadvanced1.trace.TraceStatus;
import hello.springcoreadvanced1.trace.logtrace.LogTrace;

/**
 * 템플릿 콜백 패턴 - 템플릿
 */
public class TraceTemplate {
    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message, TraceCallback<T> callback) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);

            T result = callback.call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
