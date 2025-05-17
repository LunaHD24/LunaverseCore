package dev.lunaa.lunaversecore.common.util;

import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.common.ValueFormat;
import dev.lunaa.lunaversecore.api.common.ValueModifier;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import dev.lunaa.lunaversecore.common.PersistentDataKey;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.codehaus.plexus.util.ExceptionUtils;

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
        if (!item.getPersistentDataContainer().has(PersistentDataKey.ITEM_ID_KEY, PersistentDataType.STRING)) return false;
        if (item.getPersistentDataContainer().get(PersistentDataKey.ITEM_ID_KEY, PersistentDataType.STRING) == null) return false;
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
        return Optional.ofNullable(NamespacedKey.fromString(Objects.requireNonNull(item.getPersistentDataContainer().get(PersistentDataKey.ITEM_ID_KEY, PersistentDataType.STRING))));
    }

    public static boolean hasStats(ItemStack item) {
        if (!isCustomItem(item)) return false;
        if (!item.getPersistentDataContainer().has(PersistentDataKey.ITEM_STATS_KEY)) return false;
        return true;
    }

    public static Optional<Map<StatType, StatValue>> getStats(ItemStack item) {
        if (!isCustomItem(item)) return Optional.empty();
        if (!hasStats(item)) return Optional.empty();
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (!container.has(PersistentDataKey.ITEM_STATS_KEY)) return Optional.empty();

        List<String> statsList = container.get(PersistentDataKey.ITEM_STATS_KEY, PersistentDataType.LIST.strings());
        if (statsList == null) return Optional.empty();

        return Optional.of(deserializeStats(statsList));
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
            serializedStats.add(String.format("%s;%s", statType.getKey(), statValue.serialize()));
        }
        return serializedStats;
    }

    public static Map<StatType, StatValue> deserializeStats(List<String> serializedStats) {
        Map<StatType, StatValue> deserializedStats = new HashMap<>();

        for (String serializedStat : serializedStats) {
            String[] statParts = serializedStat.split(";");

            if (statParts.length != 4) {
                LunaverseCore.getLunaLogger().warn("Couldn't deserialize stat " + serializedStat + " (invalid array size, expected 4 but got " + statParts.length + ")");
                continue;
            }

            Optional<RegistryEntry> statEntryOptional = LunaverseCore.getRegistry().get(NamespacedKey.fromString(statParts[0]));

            if (statEntryOptional.isEmpty()) {
                LunaverseCore.getLunaLogger().warn("Couldn't deserialize stat " + statParts[0] + " (not found in registry)");
                continue;
            }
            if (!(statEntryOptional.get() instanceof StatType statType)) {
                LunaverseCore.getLunaLogger().warn("Couldn't deserialize stat " + statParts[0] + " (not a stat type)");
                continue;
            }

            try {
                deserializedStats.put(statType, new StatValue(Float.parseFloat(statParts[1]), ValueModifier.fromSymbol(statParts[2].charAt(0)), ValueFormat.valueOf(statParts[3])));
            } catch (Exception e) {
                LunaverseCore.getLunaLogger().warn("Couldn't deserialize stat " + statParts[0] + " (invalid value format)\n" + ExceptionUtils.getFullStackTrace(e));
            }
        }
        return deserializedStats;
    }

}
