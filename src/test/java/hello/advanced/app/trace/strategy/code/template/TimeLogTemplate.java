package hello.advanced.app.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {
    public void execute(final Callback callback) {
        final long startTime = System.currentTimeMillis();
        callback.call();
        final long endTime = System.currentTimeMillis();
        log.info("resultTime={}", endTime - startTime);
    }
}
