package dev.lunaa.lunaversecore;

import dev.lunaa.lunaversecore.api.ApiInitializer;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import dev.lunaa.lunaversecore.registry.RegistryImpl;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.translation.TranslationRegistry;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.logging.Logger;

public final class LunaverseCore extends JavaPlugin {

    private static LunaverseCore instance;
    private static Logger logger;
    private static final TranslationRegistry translator = TranslationRegistry.create(Key.key("lunaversecore:lang"));
    public static final RegistryImpl<RegistryEntry> registry = new RegistryImpl<>();

    public static String NAMESPACE = "lunaverse";

    @Override
    public void onEnable() {
        instance = this;
        logger = Logger.getLogger("LunaverseCore");
        translator.defaultLocale(Locale.ENGLISH);

        ApiInitializer.initialize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Logger getPluginLogger() {
        return logger;
    }

    public static LunaverseCore getInstance() {
        return instance;
    }

    public static TranslationRegistry getTranslator() {
        return translator;
    }
}
