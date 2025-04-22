package dev.lunaa.lunaversecore.api.registry;

public class RegistryProvider {

    private RegistryProvider() {}

    private static Registry<?> registryInstance;

    @SuppressWarnings("unchecked")
    public static <T extends RegistryEntry> Registry<T> getRegistry() {
        return (Registry<T>) registryInstance;
    }

    protected static <T extends RegistryEntry> void setRegistry(Registry<T> registry) {
        registryInstance = registry;
    }

}
