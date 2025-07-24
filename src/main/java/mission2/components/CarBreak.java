package mission2.components;

public enum CarBreak {
    MANDO(1, "Mando"),
    CONTINENTAL(2, "Continental"),
    BOSCH(3, "Bosch");

    public final int executionCode;
    public final String description;

    CarBreak(int executionCode, String description) {
        this.executionCode = executionCode;
        this.description = description;
    }
}

