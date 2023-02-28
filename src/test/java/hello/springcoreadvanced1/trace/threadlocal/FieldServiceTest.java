package hello.springcoreadvanced1.trace.threadlocal;

import hello.springcoreadvanced1.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");

        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        Thread.sleep(20);
        threadB.start();

        threadA.join();
        threadB.join();
    }
}
