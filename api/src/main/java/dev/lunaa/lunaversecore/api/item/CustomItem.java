package dev.lunaa.lunaversecore.api.item;

import dev.lunaa.lunaversecore.LunaverseApi;
import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.Map;

public interface CustomItem extends RegistryEntry {

    NamespacedKey ITEM_ID_KEY = new NamespacedKey(LunaverseApi.getNamespace(), "item_id");
    NamespacedKey ITEM_STATS_KEY = new NamespacedKey(LunaverseApi.getNamespace(), "item_stats");


    Material getMaterial();

    default String getNameTranslationKey() {
        return "item.default.name";
    }

    default String getDescriptionTranslationKey() {
        return "item.default.description";
    }

    Map<StatType, StatValue> getStats();

}
