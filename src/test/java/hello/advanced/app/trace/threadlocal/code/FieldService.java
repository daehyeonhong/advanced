package hello.advanced.app.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

import static hello.advanced.app.utility.Utility.sleep;

@Slf4j
public class FieldService {
    private String nameStore;

    public String logic(final String name) {
        log.info("저장 name={} -> nameStore={}", name, this.nameStore);
        this.nameStore = name;
        sleep(1000);
        log.info("조회 nameStore={}", this.nameStore);
        return this.nameStore;
    }


}
