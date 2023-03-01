package hello.springcoreadvanced1.trace.strategy.code.template;

/**
 * 템플릿 콜백 패턴 - 콜백
 */
@FunctionalInterface
public interface Callback {

    /**
     * 비즈니스 로직
     */
    void call();
}
