package dev.lunaa.lunaversecore.api.item.weapon;

import dev.lunaa.lunaversecore.api.damage.DamageContext;
import dev.lunaa.lunaversecore.api.item.base.CustomItemBase;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface MeleeWeapon extends Weapon {

    default void use(PlayerInteractEvent event, CustomItemBase item) {}

    default void useOnEntity(PlayerInteractAtEntityEvent event, CustomItemBase item) {}

    default void damageEntity(DamageContext context) {}

    default void killedEntity(DamageContext context) {}

}
