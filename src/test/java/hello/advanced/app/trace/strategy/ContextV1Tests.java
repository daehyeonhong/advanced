package hello.advanced.app.trace.strategy;

import hello.advanced.app.trace.strategy.code.strategy.ContextV1;
import hello.advanced.app.trace.strategy.code.strategy.Strategy;
import hello.advanced.app.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.app.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void strategyV1() {
        final Strategy strategyLogic1 = new StrategyLogic1();
        final ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        final Strategy strategyLogic2 = new StrategyLogic2();
        final ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
        assertThat(contextV1).isNotSameAs(contextV2);
    }

    @Test
    void strategyV2() {
        final Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("Business Logic1 Start");
            }
        };
        log.info("{}", strategyLogic1.getClass());
        final ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();
        final Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("Business Logic2 Start");
            }
        };
        log.info("{}", strategyLogic2.getClass());
        final ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    @Test
    void strategyV3() {
        new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("Business Logic1 Start");
            }
        }).execute();
        new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("Business Logic2 Start");
            }
        }).execute();
    }

    @Test
    void strategyV4() {
        final ContextV1 contextV1 = new ContextV1(() -> log.info("Business Logic1 Start"));
        contextV1.execute();
        final ContextV1 contextV2 = new ContextV1(() -> log.info("Business Logic2 Start"));
        contextV2.execute();
    }
}
