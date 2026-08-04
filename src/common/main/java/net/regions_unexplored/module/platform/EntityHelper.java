package net.regions_unexplored.module.platform;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.regions_unexplored.RegionsUnexploredNeo;

import java.util.function.Supplier;

public final class EntityHelper {
    private EntityHelper() {
    }

    public static <T extends Mob> void registerPlacement(
        Supplier<EntityType<T>> type,
        SpawnPlacementType placementType,
        Heightmap.Types heightmap,
        SpawnPlacements.SpawnPredicate<T> predicate
    ) {
        RegionsUnexploredNeo.SPAWN_PLACEMENTS.add(
            event -> event.register(
                type.get(),
                placementType,
                heightmap,
                predicate,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
            )
        );
    }

    public static <T extends LivingEntity> void registerAttributes(
        Supplier<EntityType<T>> type,
        Supplier<AttributeSupplier.Builder> builder
    ) {
        RegionsUnexploredNeo.ENTITY_ATTRIBUTES.add(
            event -> event.put(
                type.get(),
                builder.get().build()
            )
        );
    }
}
