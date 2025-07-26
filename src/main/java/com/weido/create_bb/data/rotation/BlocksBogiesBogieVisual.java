package com.weido.create_bb.data.rotation;

import net.createmod.catnip.nbt.NBTHelper;
import com.simibubi.create.content.trains.bogey.BogeyVisual;

import com.weido.create_bb.data.Constants;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import com.mojang.blaze3d.vertex.PoseStack;

public abstract class BlocksBogiesBogieVisual implements BogeyVisual {
    public abstract void update(boolean forwards, float wheelAngle, PoseStack poseStack);

    @Override
    public void update(CompoundTag bogieData, float wheelAngle, PoseStack poseStack) {
        this.update(this.isForwards(bogieData), wheelAngle, poseStack);
    }

    private boolean isForwards(CompoundTag bogieData) {
        boolean isForwards = bogieData.contains(Constants.BOGIE_DIRECTION_KEY) && bogieData.getBoolean(Constants.BOGIE_DIRECTION_KEY);

        Direction direction = bogieData.contains(Constants.BOGIE_ASSEMBLY_DIRECTION_KEY)
            ? NBTHelper.readEnum(bogieData, Constants.BOGIE_ASSEMBLY_DIRECTION_KEY, Direction.class)
            : Direction.NORTH;

        boolean isPositive = isDirectionPositive(direction);
        return isPositive != isForwards;
    }

    public static boolean isDirectionPositive(Direction direction) {
        return switch (direction) {
            case NORTH, WEST, UP -> true;
            case SOUTH, DOWN, EAST -> false;
        };
    }
}
