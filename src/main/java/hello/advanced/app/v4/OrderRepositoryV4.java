package hello.advanced.app.v4;

import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static hello.advanced.app.utility.Utility.sleep;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;

    public void save(final String itemId) {
        final AbstractTemplate<Void> template = new AbstractTemplate<>(this.trace) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")) throw new IllegalStateException("예외발생");
                sleep(1000);
                return Void.TYPE.cast(null);
            }
        };
        template.execute("OrderRepository.request()");
    }
}
