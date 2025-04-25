package dev.lunaa.lunaversecore.api;

import dev.lunaa.lunaversecore.api.registry.RegistryService;

public class ApiServiceInitializer {

    public static void initialize() {
        new RegistryService().initialize();
    }

}
