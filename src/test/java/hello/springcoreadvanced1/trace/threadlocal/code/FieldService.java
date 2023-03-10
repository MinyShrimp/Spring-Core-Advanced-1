package hello.springcoreadvanced1.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {
    private String nameStore;

    public String logic(String name) {
        log.info("์ ์ฅ name = {} -> nameStore = {}", name, nameStore);
        nameStore = name;
        sleep();
        log.info("์กฐํ nameStore = {}", nameStore);
        return nameStore;
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("", e);
        }
    }
}
