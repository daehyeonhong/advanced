package hello.advanced.app.trace.threadlocal;

import hello.advanced.app.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static hello.advanced.app.utility.Utility.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ThreadLocalTest {
    private final ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        final String userA1 = this.threadLocalService.logic("userA");
        final Runnable userA = () -> this.threadLocalService.logic("userA");
        final Runnable userB = () -> this.threadLocalService.logic("userB");
        final Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        final Thread threadB = new Thread(userB);
        threadB.setName("thread-B");
        assertThat(userA1).isEqualTo("userA");

        threadA.start();
        sleep(100);
        threadB.start();
        sleep(3000);
        log.info("main exit");

    }
}
