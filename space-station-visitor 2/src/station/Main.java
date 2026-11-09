package station;

import java.util.List;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        List<StationModule> modules = List.of(
                new HabitatModule("Orion", 4, 35),
                new LaboratoryModule("Kepler", 3, 10),
                new GreenhouseModule("Eden", 6, 7));

        EnergyConsumptionVisitor energy = new EnergyConsumptionVisitor();
        MaintenanceVisitor maintenance = new MaintenanceVisitor();
        InventoryReportVisitor inventory = new InventoryReportVisitor();

        for (StationModule module : modules) {
            module.accept(energy);
            module.accept(maintenance);
            module.accept(inventory);
        }

        System.out.println("SPACE STATION MODULES");
        inventory.lines().forEach(System.out::println);
        System.out.println("Daily energy demand: " + energy.totalKwh() + " kWh");
        System.out.println("Maintenance tasks:");
        maintenance.tasks().forEach(System.out::println);
    }
}
