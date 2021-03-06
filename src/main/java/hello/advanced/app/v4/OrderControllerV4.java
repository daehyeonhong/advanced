package hello.advanced.app.v4;

import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import hello.advanced.app.v5.OrderServiceV5;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV5 orderService;
    private final LogTrace trace;

    @GetMapping(value = "/v4/request")
    public String request(final String itemId) {
        final AbstractTemplate<String> template = new AbstractTemplate<>(this.trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }
}
