package station;

import java.util.List;

public final class VisitorTests {
    private VisitorTests() { }

    public static void main(String[] args) {
        energyAcrossMixedElements();
        maintenanceBoundaries();
        reportAndImmutableResults();
        invalidInputs();
        System.out.println("PASS: energy, double dispatch, maintenance boundaries, reports, validation");
    }

    private static void energyAcrossMixedElements() {
        EnergyConsumptionVisitor visitor = new EnergyConsumptionVisitor();
        List<StationModule> modules = List.of(
                new HabitatModule("H", 4, 0),
                new LaboratoryModule("L", 3, 0),
                new GreenhouseModule("G", 6, 0));
        modules.forEach(module -> module.accept(visitor));
        check(visitor.totalKwh() == 124, "Mixed collection energy must be 124");
        EnergyConsumptionVisitor empty = new EnergyConsumptionVisitor();
        check(empty.totalKwh() == 0, "Empty traversal must consume zero");
        new LaboratoryModule("Large", Integer.MAX_VALUE, 0).accept(empty);
        check(empty.totalKwh() == 25_769_803_764L, "Arithmetic must not overflow int");
    }

    private static void maintenanceBoundaries() {
        MaintenanceVisitor visitor = new MaintenanceVisitor();
        List<StationModule> modules = List.of(
                new HabitatModule("H before", 0, 29),
                new HabitatModule("H due", 0, 30),
                new LaboratoryModule("L before", 0, 13),
                new LaboratoryModule("L due", 0, 14),
                new GreenhouseModule("G before", 0, 6),
                new GreenhouseModule("G due", 0, 7));
        modules.forEach(module -> module.accept(visitor));
        check(visitor.tasks().equals(List.of(
                "H due: inspect air filters",
                "L due: calibrate laboratory equipment",
                "G due: clean irrigation system")), "Maintenance thresholds and dispatch");
    }

    private static void reportAndImmutableResults() {
        InventoryReportVisitor visitor = new InventoryReportVisitor();
        new HabitatModule("H", 2, 0).accept(visitor);
        new LaboratoryModule("L", 1, 0).accept(visitor);
        new GreenhouseModule("G", 3, 0).accept(visitor);
        check(visitor.lines().equals(List.of("Habitat H: residents = 2",
                "Laboratory L: active experiments = 1", "Greenhouse G: growing racks = 3")),
                "Third visitor must handle all types");
        try {
            visitor.lines().clear();
            throw new AssertionError("Results must be immutable");
        } catch (UnsupportedOperationException expected) {
        }
    }

    private static void invalidInputs() {
        rejects(() -> new HabitatModule(" ", 0, 0));
        rejects(() -> new LaboratoryModule(null, 0, 0));
        rejects(() -> new GreenhouseModule("G", -1, 0));
        rejects(() -> new HabitatModule("H", 0, -1));
    }

    private static void rejects(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError("Invalid input should be rejected");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
