package hello.springcoreadvanced1.trace.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 비즈니스 로직 2
 */
@Slf4j
public class SubClassLogic2 extends AbstractTemplate {
    @Override
    protected void call() {
        log.info("비즈니스 로직 2 실행");
    }
}
