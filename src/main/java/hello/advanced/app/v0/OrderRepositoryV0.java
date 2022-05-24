package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    public void save(final String itemId) {
        if (itemId.equals("ex")) throw new IllegalStateException("예외발생");
        final int millis = 1000;
        this.sleep(millis);
    }

    private void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
