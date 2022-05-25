package hello.advanced.app.v1;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(final String itemId) {

        TraceStatus status = null;
        try {
            status = this.trace.begin("OrderRepository.request()");
            if (itemId.equals("ex")) throw new IllegalStateException("예외발생");
            this.sleep();
            this.trace.end(status);
        } catch (final Exception exception) {
            this.trace.exception(status, exception);
            throw exception;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
