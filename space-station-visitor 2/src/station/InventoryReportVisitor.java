package station;

import java.util.ArrayList;
import java.util.List;

public final class InventoryReportVisitor implements ModuleVisitor {
    private final List<String> lines = new ArrayList<>();

    @Override
    public void visitHabitat(HabitatModule module) {
        lines.add("Habitat " + module.name() + ": residents = " + module.residents());
    }

    @Override
    public void visitLaboratory(LaboratoryModule module) {
        lines.add("Laboratory " + module.name() + ": active experiments = " + module.activeExperiments());
    }

    @Override
    public void visitGreenhouse(GreenhouseModule module) {
        lines.add("Greenhouse " + module.name() + ": growing racks = " + module.growingRacks());
    }

    public List<String> lines() {
        return List.copyOf(lines);
    }
}
