package hello.advanced.app.trace.logtrace;

import hello.advanced.app.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThreadLocalLogTraceTest {
    private final ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    @Test
    void beginEndLevel() {
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
        assertThat(status1.getMessage()).isEqualTo("hello1");
    }

    @Test
    void beginExceptionLevel() {
        final TraceStatus status1 = trace.begin("hello1");
        final TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
        assertThat(status1.getMessage()).isEqualTo("hello1");
    }

}
