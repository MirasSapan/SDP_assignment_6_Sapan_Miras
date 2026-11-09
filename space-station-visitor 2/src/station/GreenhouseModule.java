package station;

public record GreenhouseModule(String name, int growingRacks, int daysSinceService)
        implements StationModule {
    public GreenhouseModule {
        Validation.name(name);
        Validation.nonNegative(growingRacks);
        Validation.nonNegative(daysSinceService);
    }

    @Override
    public void accept(ModuleVisitor visitor) {
        visitor.visitGreenhouse(this);
    }
}
