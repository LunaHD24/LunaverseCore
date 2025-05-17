package dev.lunaa.lunaversecore.test;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.item.base.CustomItemBase;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import dev.lunaa.lunaversecore.common.util.ItemUtils;
import dev.lunaa.lunaversecore.item.ItemParser;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;

@SuppressWarnings("UnstableApiUsage")
public class ItemCommand {

    private static Component getItemInfo(ItemStack item) {
        CustomItemBase customItem = ItemParser.getFrom(item).orElse(null);
        if (customItem == null) return Component.text("Not a custom item");
        Component infoComponent = Component.text("Key: " + customItem.getKey());

        Optional<Map<StatType, StatValue>> statsOptional = ItemUtils.getStats(item);
        if (statsOptional.isEmpty()) return infoComponent;
        infoComponent = infoComponent.appendNewline().append(Component.text("Stats:"));

        for (StatType statType : statsOptional.get().keySet()) {
            infoComponent = infoComponent.appendNewline().append(Component.text("- " + statType.getKey() + " : " + statsOptional.get().get(statType).ofString()));
        }

        return infoComponent;
    }

    private static final LiteralArgumentBuilder<CommandSourceStack> giveNode = Commands.literal("give")
            .then(Commands.argument("item_key", StringArgumentType.string())
            .executes(ctx -> {
                if (!(ctx.getSource().getExecutor() instanceof Player player)) return 0;
                Optional<RegistryEntry> entryOptional = LunaverseCore.registry.get(NamespacedKey.fromString(ctx.getArgument("item_key", String.class)));
                if (entryOptional.isEmpty()) {
                    player.sendMessage("Item not found");
                    return 0;
                }
                RegistryEntry entry = entryOptional.get();
                player.getInventory().addItem(ItemParser.getItem( (CustomItemBase) entry, player.locale()));

                return Command.SINGLE_SUCCESS;
            })
    );

    private static final LiteralArgumentBuilder<CommandSourceStack> infoNode = Commands.literal("info")
            .executes(ctx -> {
                if (!(ctx.getSource().getExecutor() instanceof Player player)) return 0;
                ItemStack handItem = player.getEquipment().getItemInMainHand();
                Optional<CustomItemBase> customItemOptional = ItemParser.getFrom(handItem);
                if (customItemOptional.isEmpty()) {
                    player.sendMessage("Not a custom item");
                    return 0;
                }
                player.sendMessage(getItemInfo(handItem));
                return Command.SINGLE_SUCCESS;
            });


    public static final LiteralArgumentBuilder<CommandSourceStack> rootNode = Commands.literal("customitem")
            .requires(ctx -> ctx.getExecutor() instanceof Player)
            .then(giveNode)
            .then(infoNode);

}
