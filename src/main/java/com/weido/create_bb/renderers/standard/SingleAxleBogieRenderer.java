package com.weido.create_bb.renderers.standard;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import com.weido.create_bb.registry.BogiePartials;
import com.weido.create_bb.data.rotation.BlocksBogiesBogieRenderer;

public class SingleAxleBogieRenderer extends BlocksBogiesBogieRenderer {
    @Override
    public void render(boolean forwards, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) { }

    public static class SingleAxleSmallOffset extends SingleAxleBogieRenderer {
        @Override
        public void render(boolean forwards, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
            super.render(forwards, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

            VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());

            CachedBuffers.partial(BogiePartials.SMALL_OFFSET_2_FRAME, Blocks.AIR.defaultBlockState())
                    .scale(1-1 / 512f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z))
                    .translate(-.5f, .25f, forwards ? -1 : 0)
                    .center()
                    .rotateZDegrees(wheelAngle)
                    .uncenter()
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(AllPartialModels.SMALL_BOGEY_WHEELS, Blocks.AIR.defaultBlockState())
                    .translate(0, .75f, forwards ? -1 : 1)
                    .rotateXDegrees(wheelAngle)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);
        }
    }

    public static class SingleAxleSmallBogie extends SingleAxleBogieRenderer {
        @Override
        public void render(boolean forwards, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
            super.render(forwards, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

            VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());
            SuperByteBuffer shaft1 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));

            CachedBuffers.partial(BogiePartials.SMALL_BOGIE_2_FRAME, Blocks.AIR.defaultBlockState())
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

            CachedBuffers.partial(AllPartialModels.SMALL_BOGEY_WHEELS, Blocks.AIR.defaultBlockState())
                    .translate(0, .75f, 0)
                    .rotateXDegrees(wheelAngle)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);
        }
    }
}
