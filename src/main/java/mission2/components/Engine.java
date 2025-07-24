package mission2.components;

public enum Engine {
    GM(1, "GM"),
    TOYOTA(2, "TOYOTA"),
    WIA(3, "WIA"),
    FAIL(4, "고장난 엔진");

    public final int executionCode;
    public final String description;

    Engine(int executionCode, String description) {
        this.executionCode = executionCode;
        this.description = description;
    }
}
