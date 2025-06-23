package dev.lunaa.lunaversecore.test;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.entity.CustomEntity;
import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.Optional;

@SuppressWarnings("UnstableApiUsage")
public class EntityCommand {

    private static final LiteralArgumentBuilder<CommandSourceStack> spawnNode = Commands.literal("spawn")
            .then(Commands.argument("entity_key", StringArgumentType.string())
                    .executes(ctx -> {
                        if (!(ctx.getSource().getExecutor() instanceof Player player)) return 0;
                        Optional<RegistryEntry> entryOptional = LunaverseCore.getRegistry().get(NamespacedKey.fromString(ctx.getArgument("entity_key", String.class)));
                        if (entryOptional.isEmpty() || !(entryOptional.get() instanceof CustomEntity customEntity)) return 0;
                        LunaverseCore.getEntitySpawnManager().spawn(customEntity, player.getLocation());
                        return Command.SINGLE_SUCCESS;
                    })
            );

    public static final LiteralArgumentBuilder<CommandSourceStack> rootNode = Commands.literal("customentity")
            .requires(ctx -> ctx.getExecutor() instanceof Player)
            .then(spawnNode);

}
