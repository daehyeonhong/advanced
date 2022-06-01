package hello.advanced.app.trace.strategy;

import hello.advanced.app.trace.strategy.code.template.Callback;
import hello.advanced.app.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TemplateCallbackTest {
    /**
     * Template Callback Pattern - AnonymousInnerClass
     */
    @Test
    void callbackV1() {
        final TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("Business Logic1 Start");
            }
        });
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("Business Logic2 Start");
            }
        });
    }

    /**
     * Template Callback Pattern - Lambda
     */
    @Test
    void callbackV2() {
        final TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("Business Logic1 Start"));
        template.execute(() -> log.info("Business Logic2 Start"));
    }
}
