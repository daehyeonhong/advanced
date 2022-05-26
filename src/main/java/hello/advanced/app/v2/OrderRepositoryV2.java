package hello.advanced.app.v2;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static hello.advanced.app.utility.Utility.sleep;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;

    public void save(final TraceId traceId, final String itemId) {

        TraceStatus status = null;
        try {
            status = this.trace.beginSync(traceId, "OrderRepository.request()");
            if (itemId.equals("ex")) throw new IllegalStateException("예외발생");
            sleep(1000);
            this.trace.end(status);
        } catch (final Exception exception) {
            this.trace.exception(status, exception);
            throw exception;
        }
    }

}
