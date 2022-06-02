package hello.advanced.app.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

import static hello.advanced.app.utility.Utility.sleep;

@Slf4j
public class ThreadLocalService {
    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(final String name) {
        log.info("저장 name={} -> nameStore={}", name, this.nameStore.get());
        this.nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", this.nameStore.get());
        return this.nameStore.get();
    }


}
