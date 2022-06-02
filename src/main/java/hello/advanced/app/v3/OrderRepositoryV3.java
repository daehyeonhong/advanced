package hello.advanced.app.v3;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static hello.advanced.app.utility.Utility.sleep;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace trace;

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
