package hello.advanced.app.v3;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(final String itemId) {

        TraceStatus status = null;
        try {
            status = this.trace.begin("OrderService.request()");
            this.orderRepository.save(itemId);
            this.trace.end(status);
        } catch (final Exception exception) {
            this.trace.exception(status, exception);
            throw exception;
        }

    }
}
