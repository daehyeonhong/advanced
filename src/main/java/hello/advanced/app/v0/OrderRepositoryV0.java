package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static hello.advanced.app.utility.Utility.sleep;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    public void save(final String itemId) {
        if (itemId.equals("ex")) throw new IllegalStateException("예외발생");
        sleep(1000);
    }
}
