package hello.advanced.app.trace.logtrace;

import hello.advanced.app.trace.TraceId;
import hello.advanced.app.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import static hello.advanced.app.common.enumerate.PREFIX.COMPLETE_PREFIX;
import static hello.advanced.app.common.enumerate.PREFIX.EXCEPTION_PREFIX;
import static hello.advanced.app.common.enumerate.PREFIX.START_PREFIX;

@Slf4j
public class FieldLogTrace implements LogTrace {
    private TraceId traceIdHolder;

    private static String addSpace(final String prefix, final int level) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            stringBuilder.append((i == level - 1) ? "|" + prefix : "|    ");
        }
        return stringBuilder.toString();
    }

    @Override
    public TraceStatus begin(final String message) {
        syncTraceId();
        final TraceId traceId = this.traceIdHolder;
        final long startTimeMillis = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX.getValue(), traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMillis, message);
    }

    private void syncTraceId() {
        if (this.traceIdHolder == null) this.traceIdHolder = new TraceId();
        else this.traceIdHolder = this.traceIdHolder.createNextId();
    }

    @Override
    public void end(final TraceStatus status) {
        this.complete(status, null);
    }

    @Override
    public void exception(final TraceStatus status, final Exception exception) {
        this.complete(status, exception);
    }

    private void complete(final TraceStatus traceStatus, final Exception exception) {
        final long resultTimeMillis = System.currentTimeMillis() - traceStatus.getStartTimeMillis();
        final TraceId traceId = traceStatus.getTraceId();
        if (exception == null)
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX.getValue(), traceId.getLevel()), traceStatus.getMessage(), resultTimeMillis);
        else
            log.info("[{}] {}{} time={}ms exception={}", traceId.getId(), addSpace(EXCEPTION_PREFIX.getValue(), traceId.getLevel()), traceStatus.getMessage(), resultTimeMillis, exception);
        this.releaseTraceId();
    }

    private void releaseTraceId() {
        if (this.traceIdHolder.isFirstLevel()) this.traceIdHolder = null;// destroy
        else this.traceIdHolder = this.traceIdHolder.createPreviousId();
    }
}
