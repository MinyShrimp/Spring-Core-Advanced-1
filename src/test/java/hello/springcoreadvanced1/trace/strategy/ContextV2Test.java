package hello.springcoreadvanced1.trace.strategy;

import hello.springcoreadvanced1.trace.strategy.code.ContextV2;
import hello.springcoreadvanced1.trace.strategy.code.Strategy;
import hello.springcoreadvanced1.trace.strategy.code.StrategyLogic1;
import hello.springcoreadvanced1.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * {@link Strategy}, {@link ContextV2} Test
 */
@Slf4j
public class ContextV2Test {

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴, 익명 내부 클래스 사용
     */
    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        });
    }

    /**
     * 전략 패턴, 람다 사용
     */
    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직 1 실행"));
        context.execute(() -> log.info("비즈니스 로직 2 실행"));
    }
}
