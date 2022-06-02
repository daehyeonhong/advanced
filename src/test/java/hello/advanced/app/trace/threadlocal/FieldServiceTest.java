package hello.advanced.app.trace.threadlocal;

import hello.advanced.app.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static hello.advanced.app.utility.Utility.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class FieldServiceTest {
    private final FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        final String userA1 = this.fieldService.logic("userA");
        final Runnable userA = () -> this.fieldService.logic("userA");
        final Runnable userB = () -> this.fieldService.logic("userB");
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
