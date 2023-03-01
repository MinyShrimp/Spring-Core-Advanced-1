package hello.springcoreadvanced1.app.v5;

import hello.springcoreadvanced1.trace.callback.TraceTemplate;
import hello.springcoreadvanced1.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.template = new TraceTemplate(logTrace);
    }

    public void save(String itemId) {
        template.execute(
                "OrderRepository.save()",
                () -> {
                    if (itemId.equals("ex")) {
                        throw new IllegalStateException("예외 발생!");
                    }
                    sleep(1000);
                    return null;
                }
        );
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.info("Thread Sleep Interrupted", e);
        }
    }
}
