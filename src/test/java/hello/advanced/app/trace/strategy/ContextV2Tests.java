package hello.advanced.app.trace.strategy;

import hello.advanced.app.trace.strategy.code.strategy.ContextV2;
import hello.advanced.app.trace.strategy.code.strategy.Strategy;
import hello.advanced.app.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.app.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ContextV2Tests {
    /**
     * 전략 패턴 적용
     */
    @Test
    void strategyV1() {
        final ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 익명 내부 클래스
     */
    @Test
    void strategyV2() {
        final ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("Business Logic 1Start");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("Business Logic 2Start");
            }
        });
    }

    /**
     * Lambda
     */
    @Test
    void strategyV3() {
        final ContextV2 context = new ContextV2();
        context.execute(() -> log.info("Business Logic 1Start"));
        context.execute(() -> log.info("Business Logic 2Start"));
    }
}
