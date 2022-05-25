package hello.advanced.app.trace.hellotrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class HelloTraceV2Test {

    @Test
    void beginEnd() {
        final HelloTraceV2 trace = new HelloTraceV2();
        final TraceStatus status = trace.begin("hello");
        final TraceStatus syncStatus = trace.beginSync(status.getTraceId(), "hello2");
        trace.end(status);
        trace.end(syncStatus);
        assertThat(status.getMessage()).isEqualTo("hello");
    }

    @Test
    void beginException() {
        final HelloTraceV2 trace = new HelloTraceV2();
        final TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
        assertThat(status.getMessage()).isEqualTo("hello");
    }
}
