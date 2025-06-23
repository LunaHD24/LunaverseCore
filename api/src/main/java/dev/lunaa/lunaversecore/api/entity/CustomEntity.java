package dev.lunaa.lunaversecore.api.entity;

import dev.lunaa.lunaversecore.api.registry.RegistryEntry;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.Optional;
import java.util.function.Consumer;

public interface CustomEntity extends RegistryEntry {

    EntityType getEntityType();

    Optional<Consumer<? super Entity>> getSpawnFunction();

    default Optional<String> getNameTranslationKey() {
        return Optional.empty();
    }

}
