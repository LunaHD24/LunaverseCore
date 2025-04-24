package dev.lunaa.lunaversecore;

import dev.lunaa.lunaversecore.api.common.logging.Logger;

public class LunaverseApi {

    private static String NAMESPACE;
    private static Logger logger;

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

}
