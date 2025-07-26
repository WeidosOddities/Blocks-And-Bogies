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

public class SingleAxleTrailingRenderer implements BogeyRenderer {
    @Override
    public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) { }
    public static class SingleAxleSmallTrailing extends SingleAxleTrailingRenderer {
        @Override
        public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
            super.render(bogeyData, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

            VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());
            SuperByteBuffer shaft1 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));

            CachedBuffers.partial(BogiePartials.SMALL_TRAILING_2_FRAME, Blocks.AIR.defaultBlockState())
                    .scale(1-1 / 512f)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.SMALL_SHARED_WHEELS, Blocks.AIR.defaultBlockState())
                    .translate(0, .75f, 0)
                    .rotateXDegrees(wheelAngle)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.X))
                    .translate(-.5f, .25f, -.5f)
                    .center()
                    .rotateXDegrees(wheelAngle)
                    .uncenter()
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
        }
    }
}
