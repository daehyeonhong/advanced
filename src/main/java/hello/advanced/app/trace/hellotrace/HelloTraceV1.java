package hello.advanced.app.trace.hellotrace;

import hello.advanced.app.common.enumerate.PREFIX;
import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import static hello.advanced.app.common.enumerate.PREFIX.*;

@Slf4j
@Component
public class HelloTraceV1 {
    public TraceStatus begin(final String message) {
        final TraceId traceId = new TraceId();
        final long startTimeMillis = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX.getValue(), traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMillis, message);
    }

    public void end(final TraceStatus traceStatus) {
        this.complete(traceStatus, null);
    }

    public void exception(final TraceStatus traceStatus, final Exception exception) {
        this.complete(traceStatus, exception);
    }

    private void complete(final TraceStatus traceStatus, final Exception exception) {
        final long resultTimeMillis = System.currentTimeMillis() - traceStatus.getStartTimeMillis();
        final TraceId traceId = traceStatus.getTraceId();
        if (exception == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX.getValue(), traceId.getLevel()), traceStatus.getMessage(), resultTimeMillis);
        } else {
            log.info("[{}] {}{} time={}ms exception={}", traceId.getId(), addSpace(EXCEPTION_PREFIX.getValue(), traceId.getLevel()), traceStatus.getMessage(), resultTimeMillis, exception);
        }
    }

    private static String addSpace(final String prefix, final int level) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            stringBuilder.append((i == level - 1) ? "|" + prefix : "|    ");
        }
        return stringBuilder.toString();
    }
}
