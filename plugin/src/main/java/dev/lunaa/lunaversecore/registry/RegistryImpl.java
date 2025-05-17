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
    public boolean register(T entry) {
        return register(entry, true);
    }

    @Override
    public boolean register(T entry, boolean overwrite) {
        NamespacedKey key = entry.getKey();
        if (!overwrite && registeredEntries.containsKey(key)) {
            LunaverseCore.getLunaLogger().debug("Duplicate: Entry with key " + key + " already registered - ignoring");
            return false;
        }
        boolean overwritten = registeredEntries.containsKey(key);

        registeredEntries.put(key, entry);

        LunaverseCore.getLunaLogger().debug("Registered " + getRegistryEntryType(entry).getSimpleName() + " " + entry.getKey());
        return overwritten;
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