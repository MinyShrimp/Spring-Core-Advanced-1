package hello.springcoreadvanced1.trace.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 메서드 패턴
 * - 추상 클래스 이용
 */
@Slf4j
public abstract class AbstractTemplate {

    /**
     * call() 메서드의 작동 시간을 구하는 메서드
     */
    public void execute() {
        long startTime = System.currentTimeMillis();

        call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    /**
     * 비즈니스 로직 구현
     */
    protected abstract void call();
}
