package dev.lunaa.lunaversecore.api.damage;

import dev.lunaa.lunaversecore.api.item.base.CustomItemBase;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public interface DamageContext {

    DamagingType getDamageType();

    Entity getAttacker();

    Entity getVictim();

    double getDamage();

    CustomItemBase getAttackItem();

    default boolean isFatal() {
        if (!(getVictim() instanceof LivingEntity livingEntity)) return false;
        return livingEntity.getHealth() - getDamage() > 0.0d;
    }

}
