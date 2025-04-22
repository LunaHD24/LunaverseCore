package dev.lunaa.lunaversecore.util;

import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.item.CustomItem;
import dev.lunaa.lunaversecore.api.attribute.StatType;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ItemUtils {

    /**
     * Checks whether an item is a custom item.
     * @param item The item to check.
     * @return If the item is a custom item.
     */
    public static boolean isCustomItem(ItemStack item) {
        if (item == null) return false;
        if (!item.hasItemMeta()) return false;
        if (!item.getPersistentDataContainer().has(CustomItem.ITEM_ID_KEY, PersistentDataType.STRING)) return false;
        if (item.getPersistentDataContainer().get(CustomItem.ITEM_ID_KEY, PersistentDataType.STRING) == null) return false;
        return true;
    }

    /**
     * Checks whether an item is registered in the registry.
     * @see ItemUtils#isCustomItem(ItemStack)
     * @param item The item to check.
     * @return If the item is registered.
     */
    public static boolean isRegistered(ItemStack item) {
        if (!isCustomItem(item)) return false;
        Optional<NamespacedKey> key = getKey(item);
        if (key.isEmpty()) return false;
        return LunaverseCore.registry.get(key.get()).isPresent();
    }

    /**
     * Gets the key of an item.
     * @param item The item to get the key from.
     * @return The items key.
     */
    public static Optional<NamespacedKey> getKey(ItemStack item) {
        if (!isCustomItem(item)) return Optional.empty();
        return Optional.ofNullable(NamespacedKey.fromString(Objects.requireNonNull(item.getPersistentDataContainer().get(CustomItem.ITEM_ID_KEY, PersistentDataType.STRING))));
    }

    /**
     * Serializes a map of stats into a list of strings.
     * @param stats The stats to serialize.
     * @return The serialized stats.
     */
    public static List<String> serializeStats(Map<StatType, StatValue> stats) {
        List<String> serializedStats = new ArrayList<>();

        for (StatType statType : stats.keySet()) {
            StatValue statValue = stats.get(statType);
            serializedStats.add(statType.getKey() + ":" + statValue.value() + ":" + statValue.format() + ":" + statValue.modifier());
        }

        return serializedStats;
    }

}
