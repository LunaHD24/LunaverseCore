package dev.lunaa.lunaversecore.item;

import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.item.CustomItem;
import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.common.PersistentDataKey;
import dev.lunaa.lunaversecore.common.util.ItemUtils;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.translation.GlobalTranslator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ItemParser {

    @SuppressWarnings("UnstableApiUsage")
    public static ItemStack getItem(CustomItem customItem, Locale locale) {
        ItemStack item = new ItemStack(customItem.getMaterial());
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        ArrayList<Component> lore = new ArrayList<>();

        meta.displayName(GlobalTranslator.render(Component.translatable(customItem.getNameTranslationKey()), locale).decoration(TextDecoration.ITALIC, false));

        Optional<List<String>> descriptionOptional = customItem.getDescriptionTranslationKeys();
        if (descriptionOptional.isPresent()) {
            List<String> description = descriptionOptional.get();
            for (String descriptionKey : description) {
                lore.add(GlobalTranslator.render(Component.translatable(descriptionKey), locale).decoration(TextDecoration.ITALIC, false));
            }
        }

        container.set(PersistentDataKey.ITEM_ID_KEY, PersistentDataType.STRING, customItem.getKey().asString());

        lore.add(Component.text(""));
        Map<StatType, StatValue> stats = customItem.getStats();
        for (StatType itemStatType : stats.keySet()) {
            StatValue statValue = stats.get(itemStatType);
            String valueText = String.valueOf(statValue.modifier().getSymbol()) + statValue.value();

            lore.add(
                    GlobalTranslator.render(Component.translatable(itemStatType.getTranslationKey()), locale).color(NamedTextColor.GRAY)
                            .append(Component.text(": ").color(NamedTextColor.GRAY))
                            .append(Component.text(valueText).color(statValue.isPositive() ? NamedTextColor.GREEN : NamedTextColor.RED))
                            .append(itemStatType.getCharacterComponent())
                            .decoration(TextDecoration.ITALIC, false)
            );
        }
        container.set(PersistentDataKey.ITEM_STATS_KEY, PersistentDataType.LIST.strings(), ItemUtils.serializeStats(stats));

        item.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().showInTooltip(false).build());

        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static Optional<CustomItem> getFrom(ItemStack item) {
        return Optional.empty();
    }

}
