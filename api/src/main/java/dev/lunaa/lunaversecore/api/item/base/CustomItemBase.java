package dev.lunaa.lunaversecore.api.item.base;

import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomItemBase extends RegistryEntry {

    Material getMaterial();

    default String getNameTranslationKey() {
        return "lunaverse.item.name.default";
    }

    default Optional<List<String>> getDescriptionTranslationKeys() {
        return Optional.empty();
    }

    Map<StatType, StatValue> getStats();

    void setStats(Map<StatType, StatValue> stats);

}
