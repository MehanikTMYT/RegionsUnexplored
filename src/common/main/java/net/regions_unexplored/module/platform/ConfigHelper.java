package net.regions_unexplored.module.platform;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class ConfigHelper {
    private ConfigHelper() {
    }

    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR
            .get()
            .resolve("regions_unexplored");
    }
}
