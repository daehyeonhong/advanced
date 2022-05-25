package hello.advanced.app.v1;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping(value = "/v1/request")
    public String request(final String itemId) {
        TraceStatus status = null;
        try {
            status = this.trace.begin("OrderController.request()");
            this.orderService.orderItem(itemId);
            this.trace.end(status);
        } catch (final Exception exception) {
            this.trace.exception(status, exception);
            throw exception;
        }
        return "ok";
    }
}
