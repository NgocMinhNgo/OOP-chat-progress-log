package TryUIIdea01.application;

public enum ProductType {
    PHONE("Phone"),
    LAPTOP("Laptop"),
    SMARTWATCH("Smart Watch"),
    CAMERA("Camera");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}