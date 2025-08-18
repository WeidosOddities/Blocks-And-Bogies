package com.weido.create_bb.data.accessors;

import com.railwayteam.railways.registry.CRGuiTextures;
import net.minecraft.core.BlockPos;

public abstract class RailwayScreenAccessor {
    private static BlockPos targetPos;

    public static BlockPos getTargetPos() {
        return targetPos;
    }

    public static void setTargetPos(BlockPos pos) {
        targetPos = pos;
    }

    public static CRGuiTextures getBackground(Object screen) {
        try {
            var field = screen.getClass().getDeclaredField("background");
            field.setAccessible(true);
            return (CRGuiTextures) field.get(screen);
        } catch (Exception ignored) {
            return null;
        }
    }
}
