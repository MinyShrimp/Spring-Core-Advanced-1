package hello.springcoreadvanced1.trace.hellotrace;

import hello.springcoreadvanced1.trace.TraceStatus;
import org.junit.jupiter.api.Test;

/**
 * {@link HelloTraceV1} Test
 */
class HelloTraceV1Test {
    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}