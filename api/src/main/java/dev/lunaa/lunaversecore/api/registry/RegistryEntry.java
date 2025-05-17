package dev.lunaa.lunaversecore.api.registry;

import net.kyori.adventure.key.Key;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

public interface RegistryEntry extends Keyed {

    @Override
    default @NotNull Key key() {
        return Keyed.super.key();
    }

    @Override
    @NotNull NamespacedKey getKey();

    RegistryEntry copy();

}
