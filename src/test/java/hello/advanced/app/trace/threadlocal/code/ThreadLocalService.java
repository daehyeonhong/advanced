package hello.advanced.app.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

import static hello.advanced.app.utility.Utility.sleep;

@Slf4j
public class ThreadLocalService {
    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(final String name) {
        log.info("์ ์ฅ name={} -> nameStore={}", name, this.nameStore.get());
        this.nameStore.set(name);
        sleep(1000);
        log.info("์กฐํ nameStore={}", this.nameStore.get());
        return this.nameStore.get();
    }


}
