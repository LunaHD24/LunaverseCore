package dev.lunaa.lunaversecore.api.translation;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.translation.TranslationStore;

import java.util.Locale;
import java.util.Map;

public interface Translator {

    void register(Locale locale, String key, Component translation);

    void registerAll(Locale locale, Map<String, Component> translations);

    void unregister(String key);

    TranslationStore<Component> getTranslationStore();

    void setDefaultLocale(Locale locale);

}
