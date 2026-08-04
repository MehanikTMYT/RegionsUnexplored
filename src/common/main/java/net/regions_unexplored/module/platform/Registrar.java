package net.regions_unexplored.module.platform;

import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.RegionsUnexploredNeo;

import java.util.function.Supplier;

public final class Registrar {
    private Registrar() {
    }

    @SuppressWarnings({
        "rawtypes",
        "unchecked"
    })
    public static <T> Supplier<T> register(
        Registry<? super T> registry,
        String name,
        Supplier<T> value
    ) {
        DeferredRegister register =
            RegionsUnexploredNeo.REGISTER_CACHE.computeIfAbsent(
                registry.key(),
                ignored -> DeferredRegister.create(
                    registry.key().identifier(),
                    RegionsUnexplored.MOD_ID
                )
            );

        return register.register(name, value);
    }
}
