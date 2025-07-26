package com.weido.create_bb.renderers.standard;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
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

public class TripleAxleBogieRenderer implements BogeyRenderer {
    @Override
    public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) { }
    public static class TripleAxleSmallBogie extends TripleAxleBogieRenderer {
        @Override
        public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
            super.render(bogeyData, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

            VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());
            SuperByteBuffer shaft1 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));
            SuperByteBuffer wheels = CachedBuffers.partial(AllPartialModels.SMALL_BOGEY_WHEELS, Blocks.AIR.defaultBlockState());

            CachedBuffers.partial(BogiePartials.SMALL_BOGIE_6_FRAME, Blocks.AIR.defaultBlockState())
                    .scale(1-1 / 512f)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            for (int i : Iterate.zeroAndOne) {
                shaft1.translate(-.5f, .25f, i * -1)
                        .center()
                        .rotateZDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);
            }
            for (int j = -1; j < 2; j++) {
                wheels.translate(0, .75f, j)
                        .rotateXDegrees(wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);
            }
        }
    }
}
