package dev.lunaa.lunaversecore.translation;

import dev.lunaa.lunaversecore.api.translation.Translator;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.translation.GlobalTranslator;
import net.kyori.adventure.translation.TranslationStore;

import java.util.Locale;
import java.util.Map;

public class TranslatorImpl implements Translator {

    private final TranslationStore<Component> translationStore;

    public TranslatorImpl(Locale defaultLocale, Key key) {
        this.translationStore = TranslationStore.component(key);
        translationStore.defaultLocale(defaultLocale);
        GlobalTranslator.translator().addSource(translationStore);
    }

    @Override
    public void register(Locale locale, String key, Component translation) {
        translationStore.register(key, locale, translation);
    }

    @Override
    public void registerAll(Locale locale, Map<String, Component> translations) {
        translationStore.registerAll(locale, translations);
    }

    @Override
    public void unregister(String key) {
        translationStore.unregister(key);
    }

    @Override
    public TranslationStore<Component> getTranslationStore() {
        return translationStore;
    }

    @Override
    public void setDefaultLocale(Locale locale) {
        translationStore.defaultLocale(locale);
    }

}
