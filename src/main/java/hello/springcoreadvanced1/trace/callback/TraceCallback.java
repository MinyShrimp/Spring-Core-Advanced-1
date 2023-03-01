package hello.springcoreadvanced1.trace.callback;

/**
 * 템플릿 콜백 패턴 - 콜백
 */
public interface TraceCallback<T> {

    /**
     * 비즈니스 로직
     */
    T call();
}
