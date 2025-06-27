package dev.lunaa.lunaversecore;

import com.google.gson.Gson;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import dev.lunaa.lunaversecore.common.logging.LunaLogger;
import dev.lunaa.lunaversecore.entity.EntityManager;
import dev.lunaa.lunaversecore.registry.RegistryImpl;
import dev.lunaa.lunaversecore.translation.TranslatorImpl;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.translation.TranslationStore;
import net.kyori.adventure.util.UTF8ResourceBundleControl;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public final class LunaverseCore extends JavaPlugin {

    private static LunaverseCore instance;
    private static Logger logger;
    private static LunaLogger lunaLogger;
    private static TranslatorImpl translator;
    private static Gson gson;
    private static EntityManager entitySpawnManager;

    private static boolean developmentMode = false;
    private static boolean debugMode = false;

    public static final RegistryImpl<RegistryEntry> registry = new RegistryImpl<>();

    private static final String NAMESPACE = "lunaverse_core";


    @Override
    public void onEnable() {
        instance = this;
        logger = Logger.getLogger("LunaverseCore");
        lunaLogger = new LunaLogger(logger);
        translator = new TranslatorImpl(Locale.US, Key.key(NAMESPACE, "translations"));
        gson = new Gson();
        entitySpawnManager = new EntityManager();

        loadConfig();
        loadDefaultTranslations();
        initializeApi();
    }

    private void initializeApi() {
        LunaverseApi.setNamespace(NAMESPACE);
        LunaverseApi.setRegistry(registry);
        LunaverseApi.setLogger(lunaLogger);
        LunaverseApi.setTranslator(translator);
    }

    private void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        if (getConfig().getBoolean("development-mode")) developmentMode = true;
        if (getConfig().getBoolean("debug-mode")) debugMode = true;
    }

    private void loadDefaultTranslations() {
        TranslationStore<Component> translationStore = translator.getTranslationStore();
        ResourceBundle bundle = ResourceBundle.getBundle("lang.default_lang.Bundle", Locale.US, UTF8ResourceBundleControl.get());

        for (String key : bundle.keySet()) {
            translationStore.register(key, Locale.US, Component.text(bundle.getString(key)).decoration(TextDecoration.ITALIC, false));
        }
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

    public static TranslatorImpl getTranslator() {
        return translator;
    }

    public static Gson getGson() {
        return gson;
    }

    public static EntityManager getEntitySpawnManager() {
        return entitySpawnManager;
    }

    public static boolean isInDevelopmentMode() {
        return developmentMode;
    }

    public static boolean isInDebugMode() {
        return debugMode;
    }

}
