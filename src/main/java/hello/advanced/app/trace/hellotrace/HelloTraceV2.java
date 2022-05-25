package hello.advanced.app.trace.hellotrace;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EXCEPTION_PREFIX = "<X-";

    public TraceStatus begin(final String message) {
        final TraceId traceId = new TraceId();
        final long startTimeMillis = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMillis, message);
    }

    public TraceStatus beginSync(final TraceId beforeTraceId, final String message) {
        final TraceId nextId = beforeTraceId.createNextId();
        final long startTimeMillis = System.currentTimeMillis();
        log.info("[{}] {}{}", nextId.getId(), addSpace(START_PREFIX, nextId.getLevel()), message);
        return new TraceStatus(nextId, startTimeMillis, message);
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
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), traceStatus.getMessage(), resultTimeMillis);
        } else {
            log.info("[{}] {}{} time={}ms exception={}", traceId.getId(), addSpace(EXCEPTION_PREFIX, traceId.getLevel()), traceStatus.getMessage(), resultTimeMillis, exception);
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
