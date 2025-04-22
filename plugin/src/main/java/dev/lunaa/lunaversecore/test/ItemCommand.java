package dev.lunaa.lunaversecore.test;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.item.CustomItem;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import dev.lunaa.lunaversecore.item.ItemParser;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ItemCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> giveNode = Commands.literal("giveitem")
            .then(Commands.argument("item", StringArgumentType.string())
                    .executes(ctx -> {
                        if (!(ctx.getSource().getExecutor() instanceof Player player)) return 0;
                        Optional<RegistryEntry> entryOptional = LunaverseCore.registry.get(NamespacedKey.fromString(ctx.getArgument("item", String.class)));
                        System.out.println("Entry empty: " + entryOptional.isEmpty());
                        RegistryEntry entry = entryOptional.get();
                        System.out.println("Entry key: " + entry.getKey());
                        player.getInventory().addItem(ItemParser.getItem( (CustomItem) entry));

                        return Command.SINGLE_SUCCESS;
                    })
            );

}
