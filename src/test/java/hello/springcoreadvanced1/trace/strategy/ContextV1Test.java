package hello.springcoreadvanced1.trace.strategy;

import hello.springcoreadvanced1.trace.strategy.code.ContextV1;
import hello.springcoreadvanced1.trace.strategy.code.Strategy;
import hello.springcoreadvanced1.trace.strategy.code.StrategyLogic1;
import hello.springcoreadvanced1.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * {@link Strategy}, {@link ContextV1} Test
 */
@Slf4j
public class ContextV1Test {
    /**
     * 일반 메서드
     */
    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직 1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = [{}]ms", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직 2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = [{}]ms", resultTime);
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1() {
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    /**
     * 전략 패턴, 익명 내부 클래스 사용 - 1
     */
    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        log.info("strategyLogic1 = {}", strategyLogic1.getClass());
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        log.info("strategyLogic2 = {}", strategyLogic2.getClass());
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    /**
     * 전략 패턴, 익명 내부 클래스 사용 - 2
     */
    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        });
        context2.execute();
    }

    /**
     * 전략 패턴, 람다 사용
     */
    @Test
    void strategyV4() {
        // (new ContextV1(() -> log.info("비즈니스 로직 1 실행"))).execute();
        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
        context1.execute();

        // (new ContextV1(() -> log.info("비즈니스 로직 2 실행"))).execute();
        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직 2 실행"));
        context2.execute();
    }
}
