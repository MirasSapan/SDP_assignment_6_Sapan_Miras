package station;

public record HabitatModule(String name, int residents, int daysSinceService)
        implements StationModule {
    public HabitatModule {
        Validation.name(name);
        Validation.nonNegative(residents);
        Validation.nonNegative(daysSinceService);
    }

    @Override
    public void accept(ModuleVisitor visitor) {
        visitor.visitHabitat(this);
    }
}
