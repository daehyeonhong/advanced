package hello.advanced.app.v1;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

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
