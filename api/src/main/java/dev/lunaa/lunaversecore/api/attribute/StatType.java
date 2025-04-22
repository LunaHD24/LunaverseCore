package dev.lunaa.lunaversecore.api.attribute;

import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import net.kyori.adventure.text.Component;

public interface StatType extends RegistryEntry {

    String getTranslationKey();

    char getCharacter();

    Component getCharacterComponent();

}
