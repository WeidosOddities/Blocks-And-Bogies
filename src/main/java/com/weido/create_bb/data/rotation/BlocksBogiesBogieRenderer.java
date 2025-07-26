package com.weido.create_bb.data.rotation;

import net.createmod.catnip.nbt.NBTHelper;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;

import com.weido.create_bb.data.Constants;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

import com.mojang.blaze3d.vertex.PoseStack;

public abstract class BlocksBogiesBogieRenderer implements BogeyRenderer {
    public abstract void render(boolean forwards, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption);

    @Override
    public void render(CompoundTag bogieData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
        this.render(this.isForwards(bogieData), wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);
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
        return switch (direction) { case NORTH, WEST, UP -> true; case SOUTH, DOWN, EAST -> false;};
    }
}
