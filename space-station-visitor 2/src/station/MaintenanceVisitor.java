package station;

import java.util.ArrayList;
import java.util.List;

public final class MaintenanceVisitor implements ModuleVisitor {
    private static final int HABITAT_SERVICE_INTERVAL_DAYS = 30;
    private static final int LAB_SERVICE_INTERVAL_DAYS = 14;
    private static final int GREENHOUSE_SERVICE_INTERVAL_DAYS = 7;
    private final List<String> tasks = new ArrayList<>();

    @Override
    public void visitHabitat(HabitatModule module) {
        if (module.daysSinceService() >= HABITAT_SERVICE_INTERVAL_DAYS) {
            tasks.add(module.name() + ": inspect air filters");
        }
    }

    @Override
    public void visitLaboratory(LaboratoryModule module) {
        if (module.daysSinceService() >= LAB_SERVICE_INTERVAL_DAYS) {
            tasks.add(module.name() + ": calibrate laboratory equipment");
        }
    }

    @Override
    public void visitGreenhouse(GreenhouseModule module) {
        if (module.daysSinceService() >= GREENHOUSE_SERVICE_INTERVAL_DAYS) {
            tasks.add(module.name() + ": clean irrigation system");
        }
    }

    public List<String> tasks() {
        return List.copyOf(tasks);
    }
}
