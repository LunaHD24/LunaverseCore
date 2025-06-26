package dev.lunaa.lunaversecore.registry;

import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.item.AbstractCustomItem;
import dev.lunaa.lunaversecore.api.registry.Registry;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RegistryImpl<T extends RegistryEntry> implements Registry<T> {

    private final Map<NamespacedKey, T> registeredEntries = new HashMap<>();
    
    @Override
    @SuppressWarnings("unchecked")
    public Optional<T> get(NamespacedKey key) {
        if (!registeredEntries.containsKey(key)) return Optional.empty();
        return Optional.of((T) registeredEntries.get(key).copy());
    }

    @Override
    public Optional<T> get(String key) {
        return get(NamespacedKey.fromString(key));
    }

    @Override
    public Optional<NamespacedKey> getKeyFor(T entry) {;
        for (Map.Entry<NamespacedKey, T> registeredEntry : registeredEntries.entrySet()) {
            if (registeredEntry.getValue().getClass().getName().equals(entry.getClass().getName())) {
                return Optional.of(registeredEntry.getKey());
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean register(NamespacedKey key, T entry) {
        return register(key, entry, true);
    }

    @Override
    public boolean register(String key, T entry) {
        return register(NamespacedKey.fromString(key), entry);
    }

    @Override
    public boolean register(NamespacedKey key, T entry, boolean overwrite) {
        if (!overwrite && registeredEntries.containsKey(key)) {
            LunaverseCore.getLunaLogger().debug("Duplicate: Entry with key " + key + " already registered - ignoring");
            return false;
        }
        boolean overwritten = registeredEntries.containsKey(key);
        if (overwritten) LunaverseCore.getLunaLogger().debug("Duplicate: Entry with key " + key + " already registered - overwriting");

        registeredEntries.put(key, entry);

        LunaverseCore.getLunaLogger().debug("Registered " + entry.getClass().getSimpleName() + " " + key.asString());
        return overwritten;
    }

    @Override
    public boolean register(String key, T entry, boolean overwrite) {
        return register(NamespacedKey.fromString(key), entry, overwrite);
    }

    private Class<? extends RegistryEntry> getRegistryEntryType(RegistryEntry entry) {
        for (Class<?> iface : entry.getClass().getInterfaces()) {
            if (RegistryEntry.class.isAssignableFrom(iface) && !iface.equals(RegistryEntry.class)) {
                return iface.asSubclass(RegistryEntry.class);
            }
        }
        if (entry instanceof AbstractCustomItem) return AbstractCustomItem.class;
        return RegistryEntry.class;
    }

}