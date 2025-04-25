package dev.lunaa.lunaversecore;

import dev.lunaa.lunaversecore.api.ApiServiceInitializer;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import dev.lunaa.lunaversecore.common.logging.LunaLogger;
import dev.lunaa.lunaversecore.registry.RegistryImpl;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.translation.TranslationRegistry;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.logging.Logger;

public final class LunaverseCore extends JavaPlugin {

    private static LunaverseCore instance;
    private static Logger logger;
    private static LunaLogger lunaLogger;
    private static boolean developmentMode = false;
    private static boolean debugMode = false;

    private static final TranslationRegistry translator = TranslationRegistry.create(Key.key("lunaversecore:lang"));
    public static final RegistryImpl<RegistryEntry> registry = new RegistryImpl<>();

    private static final String NAMESPACE = "lunaverse";


    @Override
    public void onEnable() {
        instance = this;
        logger = Logger.getLogger("LunaverseCore");
        lunaLogger = new LunaLogger(logger);
        translator.defaultLocale(Locale.ENGLISH);

        loadConfig();
        ApiServiceInitializer.initialize();
        initializeApi();
    }

    private void initializeApi() {
        LunaverseApi.setLogger(lunaLogger);
        LunaverseApi.setNamespace(NAMESPACE);
    }

    private void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        if (getConfig().getBoolean("development-mode")) developmentMode = true;
        if (getConfig().getBoolean("debug-mode")) debugMode = true;
    }


    public static Logger getPluginLogger() {
        return logger;
    }

    public static LunaLogger getLunaLogger() {
        return lunaLogger;
    }

    public static LunaverseCore getInstance() {
        return instance;
    }

    public static String getNamespace() {
        return NAMESPACE;
    }

    public static RegistryImpl<RegistryEntry> getRegistry() {
        return registry;
    }

    public static TranslationRegistry getTranslator() {
        return translator;
    }

    public static boolean isInDevelopmentMode() {
        return developmentMode;
    }

    public static boolean isInDebugMode() {
        return debugMode;
    }

}
