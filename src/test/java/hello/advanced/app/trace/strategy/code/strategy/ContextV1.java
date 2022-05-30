package hello.advanced.app.trace.strategy.code.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
@RequiredArgsConstructor
public class ContextV1 {
    private final Strategy strategy;

    public void execute() {
        final long startTime = System.currentTimeMillis();
        this.strategy.call();
        final long endTime = System.currentTimeMillis();
        log.info("resultTime={}", endTime - startTime);
    }
}
