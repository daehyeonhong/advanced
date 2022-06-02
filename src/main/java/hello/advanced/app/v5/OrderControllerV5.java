package hello.advanced.app.v5;

import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.trace.TraceTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
    private final TraceTemplate template;
    private final OrderServiceV5 orderService;

    public OrderControllerV5(final LogTrace trace, final OrderServiceV5 orderService) {
        this.template = new TraceTemplate(trace);
        this.orderService = orderService;
    }

    @GetMapping(value = "/v5/request")
    public String request(final String itemId) {
        return this.template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
