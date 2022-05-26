package hello.advanced.app.trace.logtrace;

import hello.advanced.app.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(final String message);

    void end(final TraceStatus status);

    void exception(final TraceStatus status, final Exception exception);
}
