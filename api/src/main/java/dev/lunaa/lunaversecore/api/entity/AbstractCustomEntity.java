package dev.lunaa.lunaversecore.api.entity;

import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class AbstractCustomEntity implements CustomEntity {

    private final EntityType entityType;
    private Consumer<? super Entity> spawnFunction;

    public AbstractCustomEntity(@NotNull EntityType entityType) {
        this.entityType = entityType;
    }

    public AbstractCustomEntity(@NotNull EntityType entityType, Consumer<? super Entity> spawnFunction) {
        this(entityType);
        this.spawnFunction = spawnFunction;
    }

    @Override
    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public Optional<Consumer<? super Entity>> getSpawnFunction() {
        return Optional.ofNullable(spawnFunction);
    }

    @Override
    public @NotNull NamespacedKey getKey() { // TODO: keys not directly in a RegistryEntry, instead registered with the original entry to the registry
        return null;
    }

    @Override
    public RegistryEntry copy() {
        return spawnFunction == null ? new AbstractCustomEntity(entityType) : new AbstractCustomEntity(entityType, spawnFunction);
    }

}
