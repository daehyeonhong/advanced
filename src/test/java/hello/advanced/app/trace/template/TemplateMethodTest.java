package hello.advanced.app.trace.template;

import hello.advanced.app.trace.template.code.AbstractMethod;
import hello.advanced.app.trace.template.code.SubClassLogic1;
import hello.advanced.app.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TemplateMethodTest {
    @Test
    void templateMethod() {
        this.logic1();
        this.logic2();
    }

    /**
     * Apply Template Method
     */
    @Test
    void templateMethodV1() {
        final AbstractMethod template1 = new SubClassLogic1();
        template1.execute();
        final AbstractMethod template2 = new SubClassLogic2();
        template2.execute();
    }

    /**
     * Apply Template Method
     */
    @Test
    void templateMethodV2() {
        final AbstractMethod template1 = new AbstractMethod() {
            @Override
            protected void call() {
                log.info("Business Logic1 Start");
            }
        };
        template1.execute();
        log.info("AnonymousInnerClass={}", template1);

        final AbstractMethod template2 = new AbstractMethod() {
            @Override
            protected void call() {
                log.info("Business Logic1 Start");
            }
        };
        template2.execute();
        log.info("AnonymousInnerClass={}", template2);
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
