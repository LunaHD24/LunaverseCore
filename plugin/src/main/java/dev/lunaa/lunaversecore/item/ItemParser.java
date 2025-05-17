package dev.lunaa.lunaversecore.item;

import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.item.base.CustomItemBase;
import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import dev.lunaa.lunaversecore.common.PersistentDataKey;
import dev.lunaa.lunaversecore.common.util.ItemUtils;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.translation.GlobalTranslator;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ItemParser {

    @SuppressWarnings("UnstableApiUsage")
    public static ItemStack getItem(CustomItemBase customItem, Locale locale) {
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

            lore.add(
                    GlobalTranslator.render(Component.translatable(itemStatType.getTranslationKey()), locale).color(NamedTextColor.GRAY)
                            .append(Component.text(": ").color(NamedTextColor.GRAY))
                            .append(Component.text(statValue.ofString()).color(statValue.isPositive() ? NamedTextColor.GREEN : NamedTextColor.RED))
                            .append(itemStatType.getCharacterComponent())
                            .decoration(TextDecoration.ITALIC, false)
            );
        }
        List<String> serializedStats = ItemUtils.serializeStats(stats);
        LunaverseCore.getLunaLogger().dev("Serialized stats: " + serializedStats);
        container.set(PersistentDataKey.ITEM_STATS_KEY, PersistentDataType.LIST.strings(), serializedStats);

        item.setData(DataComponentTypes.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.itemAttributes().showInTooltip(false).build());

        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static Optional<CustomItemBase> getFrom(ItemStack item) {
        if (!ItemUtils.isRegistered(item)) return Optional.empty();
        NamespacedKey itemKey = ItemUtils.getKey(item).get();
        RegistryEntry entry = LunaverseCore.getRegistry().get(itemKey).get();
        if (!(entry instanceof CustomItemBase customItem)) return Optional.empty();

        Optional<Map<StatType, StatValue>> statsOptional = ItemUtils.getStats(item);
        statsOptional.ifPresent(customItem::setStats);

        return Optional.of(customItem);
    }

}
