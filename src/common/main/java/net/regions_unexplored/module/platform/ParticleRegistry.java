package net.regions_unexplored.module.platform;

import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.regions_unexplored.client.ParticleRegistration;

import java.util.function.Function;

public final class ParticleRegistry {
    private ParticleRegistry() {
    }

    public static <T extends ParticleOptions> void register(
        ParticleType<T> type,
        Function<SpriteSet, ParticleProvider<T>> provider
    ) {
        ParticleRegistration.spriteParticleRegistrationMap.put(
            type,
            provider::apply
        );
    }
}
