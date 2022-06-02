package hello.advanced.app.v5;

import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.trace.TraceCallback;
import hello.advanced.app.trace.trace.TraceTemplate;
import org.springframework.stereotype.Repository;

import static hello.advanced.app.utility.Utility.sleep;

@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public OrderRepositoryV5(final LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(final String itemId) {
        this.template.execute("OrderRepository.request()", (TraceCallback<Object>) () -> {
            if (itemId.equals("ex")) throw new IllegalStateException("예외발생");
            sleep(1000);
            return Void.TYPE.cast(null);
        });
    }
}
