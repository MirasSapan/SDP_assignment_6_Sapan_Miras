package station;

final class Validation {
    private Validation() { }

    static void name(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Module name must not be blank");
        }
    }

    static void nonNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Module values must not be negative");
        }
    }
}
