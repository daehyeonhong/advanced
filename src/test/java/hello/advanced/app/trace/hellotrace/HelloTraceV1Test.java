package hello.advanced.app.trace.hellotrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloTraceV1Test {

    @Test
    void beginEnd() {
        final HelloTraceV1 trace = new HelloTraceV1();
        final TraceStatus status = trace.begin("hello");
        trace.end(status);
        assertThat(status.getMessage()).isEqualTo("hello");
    }

    @Test
    void beginException() {
        final HelloTraceV1 trace = new HelloTraceV1();
        final TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
        assertThat(status.getMessage()).isEqualTo("hello");
    }
}
