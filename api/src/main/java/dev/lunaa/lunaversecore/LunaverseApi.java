package dev.lunaa.lunaversecore;

import dev.lunaa.lunaversecore.api.common.logging.Logger;
import dev.lunaa.lunaversecore.api.registry.Registry;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import dev.lunaa.lunaversecore.api.translation.Translator;

public class LunaverseApi {

    private static String NAMESPACE;
    private static Registry<RegistryEntry> registry;
    private static Logger logger;
    private static Translator translator;

    public static <T extends RegistryEntry> Registry<T> getRegistry() {
        return (Registry<T>) registry;
    }

    protected static void setRegistry(Registry<RegistryEntry> registry) {
        LunaverseApi.registry = registry;
    }

    public static Logger getLogger() {
        return logger;
    }

    protected static void setLogger(Logger logger) {
        LunaverseApi.logger = logger;
    }

    public static String getNamespace() {
        return NAMESPACE;
    }

    protected static void setNamespace(String namespace) {
        NAMESPACE = namespace;
    }

    public static Translator getTranslator() {
        return translator;
    }

    protected static void setTranslator(Translator translator) {
        LunaverseApi.translator = translator;
    }
}
