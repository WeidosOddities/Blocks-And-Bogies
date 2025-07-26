package com.weido.create_bb.renderers.trailing;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Blocks;
import com.weido.create_bb.registry.BogiePartials;

public class QuadrupleAxleTrailingRenderer implements BogeyRenderer {
    @Override
    public void render(CompoundTag compoundTag, float wheelAngle, float partialTick, PoseStack posestack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) { }
    public static class QuadrupleAxleSmallTrailing extends QuadrupleAxleTrailingRenderer {
        @Override
        public void render(CompoundTag compoundTag, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
            super.render(compoundTag, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

            VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());
            SuperByteBuffer shaft1 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));
            SuperByteBuffer shaft2 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.X));
            SuperByteBuffer wheels = CachedBuffers.partial(BogiePartials.SMALL_SHARED_WHEELS, Blocks.AIR.defaultBlockState());

            CachedBuffers.partial(BogiePartials.SMALL_TRAILING_8_FRAME, Blocks.AIR.defaultBlockState())
                    .scale(1-1 / 512f)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            for (int i = -1; i < 3; i++) {
                wheels.translate(0, .75f, i-.5f)
                        .rotateXDegrees(wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft2.translate(-.5f, .25f, i-1)
                        .center()
                        .rotateXDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);
            }

            for (int j : Iterate.positiveAndNegative) {
                shaft1.translate(-.5f, .25f, j-.5f)
                        .center()
                        .rotateZDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);
            }
        }
    }
}
