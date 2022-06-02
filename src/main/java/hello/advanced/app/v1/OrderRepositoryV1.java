package hello.advanced.app.v1;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static hello.advanced.app.utility.Utility.sleep;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(final String itemId) {

        TraceStatus status = null;
        try {
            status = this.trace.begin("OrderRepository.request()");
            if (itemId.equals("ex")) throw new IllegalStateException("예외발생");
            sleep(1000);
            this.trace.end(status);
        } catch (final Exception exception) {
            this.trace.exception(status, exception);
            throw exception;
        }
    }
}
