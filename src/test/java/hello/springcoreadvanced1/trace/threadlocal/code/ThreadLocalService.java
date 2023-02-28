package hello.springcoreadvanced1.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 name = {} -> nameStore = {}", name, nameStore.get());
        nameStore.set(name);
        sleep();
        log.info("조회 nameStore = {}", nameStore.get());
        return nameStore.get();
    }

    public void remove() {
        nameStore.remove();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("", e);
        }
    }
}
