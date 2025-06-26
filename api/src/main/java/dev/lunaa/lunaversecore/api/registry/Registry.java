package dev.lunaa.lunaversecore.api.registry;

import org.bukkit.NamespacedKey;

import java.util.Optional;

public interface Registry<T extends RegistryEntry> {

    Optional<T> get(NamespacedKey key);

    Optional<T> get(String key);

    Optional<NamespacedKey> getKeyFor(T entry);

    boolean register(NamespacedKey key, T entry);

    boolean register(String key, T entry);

    boolean register(NamespacedKey key, T entry, boolean overwrite);

    boolean register(String key, T entry, boolean overwrite);

}
