package dev.lunaa.lunaversecore.api.registry;

import org.bukkit.NamespacedKey;

import java.util.Optional;

public interface Registry<T extends RegistryEntry> {

    Optional<T> get(NamespacedKey key);

    boolean register(T entry);

    boolean register(T entry, boolean overwrite);

}
