package hello.advanced.app.trace.strategy.code.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파라미터로 전달 받는 방식
 */
@Slf4j
@RequiredArgsConstructor
public class ContextV2 {

    public void execute(final Strategy strategy) {
        final long startTime = System.currentTimeMillis();
        strategy.call();
        final long endTime = System.currentTimeMillis();
        log.info("resultTime={}", endTime - startTime);
    }
}
