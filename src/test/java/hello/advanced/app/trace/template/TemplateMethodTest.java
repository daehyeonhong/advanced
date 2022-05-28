package hello.advanced.app.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TemplateMethodTest {
    @Test
    void templateMethod() {
        this.logic1();
        this.logic2();
    }

    private void logic1() {
        final long startTime = System.currentTimeMillis();
        log.info("Business Logic1 Start");
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        final long startTime = System.currentTimeMillis();
        log.info("Business Logic2 Start");
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
