package dev.lunaa.lunaversecore.api.entity;

import org.bukkit.damage.DamageSource;

@SuppressWarnings("UnstableApiUsage")
public interface CustomLivingEntity extends CustomEntity {

    double getHealth();

    void setHealth(double health);

    double getMaxHealth();

    double calculateFinalDamage(double damage, DamageSource source);

    boolean damage(double damage, DamageSource source);

}
