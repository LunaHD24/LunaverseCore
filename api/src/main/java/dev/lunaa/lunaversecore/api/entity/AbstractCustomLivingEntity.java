package dev.lunaa.lunaversecore.api.entity;

import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public abstract class AbstractCustomLivingEntity extends AbstractCustomEntity implements CustomLivingEntity {

    private final double maxHealth;
    private double health;


    public AbstractCustomLivingEntity(@NotNull EntityType entityType, double maxHealth) {
        super(entityType);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public AbstractCustomLivingEntity(@NotNull EntityType entityType, double maxHealth, Consumer<? super Entity> spawnFunction) {
        super(entityType, spawnFunction);
        this.maxHealth = maxHealth;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public double getMaxHealth() {
        return maxHealth;
    }

    @Override
    public double calculateFinalDamage(double damage, DamageSource source) {
        return damage;
    }

    @Override
    public boolean damage(double damage, DamageSource source) {
        setHealth(Math.clamp(getHealth() - calculateFinalDamage(damage, source), 0.0d, maxHealth));
        return getHealth() <= 0.0d;
    }

}
