package hello.advanced.app.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractMethod {
    @Override
    protected void call() {
        log.info("Business Logic2 Start");
    }
}
