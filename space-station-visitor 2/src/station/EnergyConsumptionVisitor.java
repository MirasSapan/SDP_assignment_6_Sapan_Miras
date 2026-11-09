package station;

public final class EnergyConsumptionVisitor implements ModuleVisitor {
    private static final long HABITAT_BASE_KWH = 20;
    private static final long KWH_PER_RESIDENT = 5;
    private static final long KWH_PER_EXPERIMENT = 12;
    private static final long KWH_PER_GROWING_RACK = 8;
    private long totalKwh;

    @Override
    public void visitHabitat(HabitatModule module) {
        totalKwh += HABITAT_BASE_KWH + module.residents() * KWH_PER_RESIDENT;
    }

    @Override
    public void visitLaboratory(LaboratoryModule module) {
        totalKwh += module.activeExperiments() * KWH_PER_EXPERIMENT;
    }

    @Override
    public void visitGreenhouse(GreenhouseModule module) {
        totalKwh += module.growingRacks() * KWH_PER_GROWING_RACK;
    }

    public long totalKwh() {
        return totalKwh;
    }
}
