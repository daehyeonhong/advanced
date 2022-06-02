package hello.advanced.app.v5;

import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.trace.TraceTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {
    private final TraceTemplate template;
    private final OrderRepositoryV5 orderRepository;

    public OrderServiceV5(final LogTrace trace, final OrderRepositoryV5 orderRepository) {
        this.template = new TraceTemplate(trace);
        this.orderRepository = orderRepository;
    }

    public void orderItem(final String itemId) {
        this.template.execute("OrderService.request()", () -> {
            orderRepository.save(itemId);
            return Void.TYPE.cast(null);
        });
    }
}
