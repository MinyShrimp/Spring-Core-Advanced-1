package hello.springcoreadvanced1.trace.strategy.code;

/**
 * 전략 패턴 인터페이스
 */
@FunctionalInterface
public interface Strategy {

    /**
     * 비즈니스 로직
     */
    void call();
}
