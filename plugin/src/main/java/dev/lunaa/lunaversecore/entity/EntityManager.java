package dev.lunaa.lunaversecore.entity;

import dev.lunaa.lunaversecore.api.entity.CustomEntity;
import dev.lunaa.lunaversecore.api.level.LevelHolder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.UUID;
import java.util.function.BiConsumer;

public class EntityManager {

    private final BiConsumer<Entity, CustomEntity> spawnFunction = (entity, customEntity) -> {
        if (customEntity.getNameTranslationKey().isEmpty()) return;

        Component nameComponent = Component.empty();
        if (customEntity instanceof LevelHolder levelHolder) {
            nameComponent = Component.text("[")
                    .color(NamedTextColor.DARK_GRAY)
                    .append(Component.text("Lvl." + levelHolder.getLevel()).color(NamedTextColor.GRAY))
                    .append(Component.text("] ").color(NamedTextColor.DARK_GRAY));
        }
        nameComponent = nameComponent.append(Component.translatable(customEntity.getNameTranslationKey().get()).color(NamedTextColor.GRAY));

        entity.customName(nameComponent); // TODO: last part appears DARK_GRAY, possible bug in adventure with component-based translations, look into when updating to 1.21.6!
        entity.setCustomNameVisible(true);
    };

    public UUID spawn(CustomEntity customEntity, Location location) {
        Entity spawnedEntity = customEntity.getSpawnFunction().isPresent() ?
                location.getWorld().spawnEntity(location, customEntity.getEntityType(), CreatureSpawnEvent.SpawnReason.CUSTOM, customEntity.getSpawnFunction().get()) :
                location.getWorld().spawnEntity(location, customEntity.getEntityType(), CreatureSpawnEvent.SpawnReason.CUSTOM);
        spawnFunction.accept(spawnedEntity, customEntity);
        return spawnedEntity.getUniqueId();
    }



}
