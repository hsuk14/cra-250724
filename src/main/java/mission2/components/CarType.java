package mission2.components;

public enum CarType {
    SEDAN(1, "Sedan"),
    SUV(2, "SUV"),
    TRUCK(3, "Truck");

    public final int executionCode;
    public final String description;

    CarType(int executionCode, String description) {
        this.executionCode = executionCode;
        this.description = description;
    }
}
