package station;

public record LaboratoryModule(String name, int activeExperiments, int daysSinceService)
        implements StationModule {
    public LaboratoryModule {
        Validation.name(name);
        Validation.nonNegative(activeExperiments);
        Validation.nonNegative(daysSinceService);
    }

    @Override
    public void accept(ModuleVisitor visitor) {
        visitor.visitLaboratory(this);
    }
}
