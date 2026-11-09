package station;

public interface ModuleVisitor {
    void visitHabitat(HabitatModule module);
    void visitLaboratory(LaboratoryModule module);
    void visitGreenhouse(GreenhouseModule module);
}
