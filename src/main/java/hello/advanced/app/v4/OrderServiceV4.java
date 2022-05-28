package hello.advanced.app.v4;

import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(final String itemId) {
        final AbstractTemplate<Void> template = new AbstractTemplate<>(this.trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return Void.TYPE.cast(null);
            }
        };
        template.execute("OrderService.request()");
    }
}
