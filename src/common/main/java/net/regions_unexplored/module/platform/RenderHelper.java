package net.regions_unexplored.module.platform;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.client.ClientHooks;

import java.util.function.Supplier;

public final class RenderHelper {
    private RenderHelper() {
    }

    public static <T extends Entity> void registerEntityRenderer(
        EntityType<? extends T> entityType,
        EntityRendererProvider<T> entityRendererFactory
    ) {
        EntityRenderers.register(
            entityType,
            entityRendererFactory
        );
    }

    public static void registerModelLayer(
        ModelLayerLocation layerLocation,
        Supplier<LayerDefinition> supplier
    ) {
        ClientHooks.registerLayerDefinition(
            layerLocation,
            supplier::get
        );
    }

    public static void registerArmorModelLayer(
        ArmorModelSet<ModelLayerLocation> armorModelSet,
        Supplier<ArmorModelSet<LayerDefinition>> provider
    ) {
        ArmorModelSet<LayerDefinition> definitions =
            provider.get();

        ClientHooks.registerLayerDefinition(
            armorModelSet.head(),
            definitions::head
        );

        ClientHooks.registerLayerDefinition(
            armorModelSet.chest(),
            definitions::chest
        );

        ClientHooks.registerLayerDefinition(
            armorModelSet.legs(),
            definitions::legs
        );

        ClientHooks.registerLayerDefinition(
            armorModelSet.feet(),
            definitions::feet
        );
    }
}
