package dev.lunaa.lunaversecore.item;

import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.item.CustomItem;
import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.util.ItemUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ItemParser {

    public static ItemStack getItem(CustomItem customItem) {
        ItemStack item = new ItemStack(customItem.getMaterial());
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        ArrayList<Component> lore = new ArrayList<>();

        meta.displayName(Component.translatable(customItem.getNameTranslationKey()).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.translatable(customItem.getDescriptionTranslationKey()).decoration(TextDecoration.ITALIC, false));

        container.set(CustomItem.ITEM_ID_KEY, PersistentDataType.STRING, customItem.getKey().asString());

        Map<StatType, StatValue> stats = customItem.getStats();
        for (StatType itemStatType : stats.keySet()) {
            StatValue statValue = stats.get(itemStatType);
            String valueText = String.valueOf(statValue.modifier().getCharacter()) + statValue.value();

            lore.add(
                    Component.translatable(itemStatType.getTranslationKey())
                            .append(Component.text(": ").color(NamedTextColor.DARK_GRAY))
                            .append(Component.text(valueText).color(statValue.isPositive() ? NamedTextColor.GREEN : NamedTextColor.RED))
                            .append(itemStatType.getCharacterComponent())
                            .decoration(TextDecoration.ITALIC, false)
            );
        }
        container.set(CustomItem.ITEM_STATS_KEY, PersistentDataType.LIST.strings(), ItemUtils.serializeStats(stats));

        meta.lore(lore);

        item.setItemMeta(meta);

        return item;
    }

    /*public static Optional<CustomItem> getFrom(ItemStack item) {

    }*/

}
