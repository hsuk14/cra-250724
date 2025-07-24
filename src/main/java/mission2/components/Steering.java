package mission2.components;

public enum Steering {
    BOSCH(1, "Bosch"),
    MOBIS(2, "Mobis");

    public final int executionCode;
    public final String description;

    Steering(int executionCode, String description) {
        this.executionCode = executionCode;
        this.description = description;
    }
}

