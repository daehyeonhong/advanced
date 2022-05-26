package hello.advanced.app.common.enumerate;

public enum PREFIX {
    START_PREFIX("-->"),
    COMPLETE_PREFIX("<--"),
    EXCEPTION_PREFIX("<X-");
    private final String value;

    PREFIX(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
