package hello.springcoreadvanced1.trace.threadlocal;

import hello.springcoreadvanced1.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private final ThreadLocalService service = new ThreadLocalService();

    @Test
    void threadLocal() throws InterruptedException {
        log.info("main start");

        Runnable userA = () -> {
            service.logic("userA");
            service.remove();
        };

        Runnable userB = () -> {
            service.logic("userB");
            service.remove();
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
    }
}
