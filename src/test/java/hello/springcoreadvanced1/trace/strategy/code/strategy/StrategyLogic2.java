package hello.springcoreadvanced1.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략 패턴을 구현한 비즈니스 로직
 */
@Slf4j
public class StrategyLogic2 implements Strategy {
    @Override
    public void call() {
        log.info("비즈니스 로직 2 실행");
    }
}
