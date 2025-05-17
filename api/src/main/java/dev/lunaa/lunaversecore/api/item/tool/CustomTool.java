package dev.lunaa.lunaversecore.api.item.tool;

import dev.lunaa.lunaversecore.api.damage.DamageContext;
import dev.lunaa.lunaversecore.api.item.base.CustomItemBase;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.components.ToolComponent;

@SuppressWarnings("UnstableApiUsage")
public interface CustomTool extends CustomItemBase {

    default void use(PlayerInteractEvent event) {}

    default void useOnBlock(PlayerInteractEvent event) {}

    default void useOnEntity(PlayerInteractEvent event) {}

    default void damageEntity(DamageContext context) {}

    default void killedEntity(DamageContext context) {}

    default void damageBlock(BlockDamageEvent event) {}

    default void breakBlock(BlockBreakEvent event) {}

    ToolComponent getToolComponent();

}
