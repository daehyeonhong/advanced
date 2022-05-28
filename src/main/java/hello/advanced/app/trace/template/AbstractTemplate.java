package hello.advanced.app.trace.template;

import hello.advanced.app.trace.TraceStatus;
import hello.advanced.app.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public T execute(final String message) {
        TraceStatus status = null;
        try {
            status = this.trace.begin(message);
            final T result = this.call();
            this.trace.end(status);
            return result;
        } catch (final Exception exception) {
            this.trace.exception(status, exception);
            throw exception;
        }
    }

    protected abstract T call();
}
