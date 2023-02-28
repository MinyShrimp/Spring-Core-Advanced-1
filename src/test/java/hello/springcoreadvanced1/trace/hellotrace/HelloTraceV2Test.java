package hello.springcoreadvanced1.trace.hellotrace;

import hello.springcoreadvanced1.trace.TraceStatus;
import org.junit.jupiter.api.Test;

/**
 * {@link HelloTraceV2} Test
 */
class HelloTraceV2Test {
    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();

        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "world");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();

        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "world");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}