package dev.lunaa.lunaversecore.api.player;

import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import net.kyori.adventure.text.Component;

public interface PlayerStatType extends RegistryEntry {

    String getTranslationKey();

    char getCharacter();

    Component getCharacterComponent();

}
