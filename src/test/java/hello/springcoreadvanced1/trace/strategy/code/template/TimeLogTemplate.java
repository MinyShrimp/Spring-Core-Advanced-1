package hello.springcoreadvanced1.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 콜백 패턴 - 템플릿
 */
@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();

        callback.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = [{}ms]", resultTime);
    }
}
