package net.regions_unexplored.module.platform;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.regions_unexplored.RegionsUnexplored;

public final class BlockHelper {
    private BlockHelper() {
    }

    public static WoodType setWoodType(
        String path,
        BlockSetType blockSetType
    ) {
        return WoodType.register(
            new WoodType(
                RegionsUnexplored.id(path).toString(),
                blockSetType
            )
        );
    }
}
