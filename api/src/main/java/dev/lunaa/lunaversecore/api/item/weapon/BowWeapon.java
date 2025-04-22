package dev.lunaa.lunaversecore.api.item.weapon;

import dev.lunaa.lunaversecore.api.damage.DamageContext;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public interface BowWeapon extends Weapon {

    default void shot(EntityShootBowEvent event) {}

    default void hitBlock(ProjectileHitEvent event) {}

    default void hitEntity(ProjectileHitEvent event) {}

    default void killedEntity(DamageContext context) {}

}
