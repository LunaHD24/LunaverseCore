package dev.lunaa.lunaversecore.api.item.base;

import dev.lunaa.lunaversecore.api.attribute.StatEntry;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import org.bukkit.Material;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomItemBase extends RegistryEntry {

    Material getMaterial();

    default String getNameTranslationKey() {
        return "lunaverse.item.name.default";
    }

    default Optional<List<String>> getDescriptionTranslationKeys() {
        return Optional.empty();
    }

    Set<StatEntry> getStats();

    void setStats(Set<StatEntry> stats);

}
