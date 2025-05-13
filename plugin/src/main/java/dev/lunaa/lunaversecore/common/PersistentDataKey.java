package dev.lunaa.lunaversecore.common;

import dev.lunaa.lunaversecore.LunaverseApi;
import org.bukkit.NamespacedKey;

public class PersistentDataKey {

    public static final NamespacedKey ITEM_ID_KEY = new NamespacedKey(LunaverseApi.getNamespace(), "item_id");
    public static final NamespacedKey ITEM_STATS_KEY = new NamespacedKey(LunaverseApi.getNamespace(), "item_stats");

}
