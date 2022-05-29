package hello.advanced.app.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ContextV1Tests {
    @Test
    void templateMethodV0() {
        this.logic1();
        this.logic2();
    }

    private void logic1() {
        final long startTime = System.currentTimeMillis();
        log.info("Business Logic1 Start");
        final long endTime = System.currentTimeMillis();
        log.info("resultTime={}", endTime - startTime);
    }

    private void logic2() {
        final long startTime = System.currentTimeMillis();
        log.info("Business Logic2 Start");
        final long endTime = System.currentTimeMillis();
        log.info("resultTime={}", endTime - startTime);
    }
}
