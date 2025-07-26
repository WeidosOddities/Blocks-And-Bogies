package com.weido.create_bb.renderers.scotchyoke;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.weido.create_bb.registry.BogiePartials;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;

import static com.weido.create_bb.data.Constants.BELT_RADIUS_IN_UV_SPACE;

public class QuadrupleAxleScotchYokeRenderer implements BogeyRenderer {
    @Override
    public void render(CompoundTag bogieData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) { }

    public static class QuadrupleAxleLargeScotchYoke extends QuadrupleAxleScotchYokeRenderer {
        @Override
        public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
            super.render(bogeyData, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

            VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());

            SuperByteBuffer wheels = CachedBuffers.partial(BogiePartials.LARGE_SHARED_WHEELS, Blocks.AIR.defaultBlockState());
            SuperByteBuffer wheels_semi = CachedBuffers.partial(BogiePartials.LARGE_SHARED_WHEELS_SEMI_BLIND, Blocks.AIR.defaultBlockState());

            SuperByteBuffer shaft1 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));
            SuperByteBuffer shaft2 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));
            SuperByteBuffer shaft3 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.X));

            float spriteSize = AllSpriteShifts.BOGEY_BELT.getTarget()
                    .getV1()
                    - AllSpriteShifts.BOGEY_BELT.getTarget()
                    .getV0();

            float scroll = BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle;
            scroll = scroll - Mth.floor(scroll);
            scroll = scroll * spriteSize * 0.5f;

            CachedBuffers.partial(BogiePartials.LARGE_8P_BELTS, Blocks.AIR.defaultBlockState())
                    .scale(1-1 / 512f)
                    .light(light)
                    .overlay(overlay)
                    .shiftUVScrolling(AllSpriteShifts.BOGEY_BELT, scroll)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.LARGE_8C_FRAME, Blocks.AIR.defaultBlockState())
                    .scale(1-1 / 512f)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.LARGE_8C_R_PIN, Blocks.AIR.defaultBlockState())
                    .translate(0, 1, 0)
                    .rotateXDegrees((wheelAngle))
                    .translate(0, .25f, 0)
                    .rotateXDegrees(-(wheelAngle))
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.LARGE_8C_L_PIN, Blocks.AIR.defaultBlockState())
                    .translate(0, 1, 0)
                    .rotateXDegrees((wheelAngle+90))
                    .translate(0, .25f, 0)
                    .rotateXDegrees(-(wheelAngle+90))
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.LARGE_8C_R_SLIDER, Blocks.AIR.defaultBlockState())
                    .translate(0, 0, .25f * Math.sin(AngleHelper.rad(wheelAngle)))
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.LARGE_8C_L_SLIDER, Blocks.AIR.defaultBlockState())
                    .translate(0, 0, .25f * Math.sin(AngleHelper.rad(wheelAngle+90)))
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            for (int i : Iterate.positiveAndNegative) {
                wheels.translate(0,1, i*2.625f)
                        .rotateXDegrees(wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                wheels_semi.translate(0,1, i*0.875f)
                        .rotateXDegrees(wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft1.translate(-.5f, .25f, (i*1.75f)-.5f)
                        .center()
                        .rotateZDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft2.translate(-.5f, .25f, (i*3.25f)-.5f)
                        .center()
                        .rotateZDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft3.translate(-.5f, .25f, (i*3.625f)-.5f)
                        .center()
                        .rotateXDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);
            }
        }
    }

    public static class QuadrupleAxleExtraLargeScotchYoke extends QuadrupleAxleScotchYokeRenderer {
        @Override
        public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
            super.render(bogeyData, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

            VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());

            SuperByteBuffer wheels = CachedBuffers.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS, Blocks.AIR.defaultBlockState());
            SuperByteBuffer wheels_semi = CachedBuffers.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS_SEMI_BLIND, Blocks.AIR.defaultBlockState());

            SuperByteBuffer shaft1 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));
            SuperByteBuffer shaft3 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.X));

            float spriteSize = AllSpriteShifts.BOGEY_BELT.getTarget()
                    .getV1()
                    - AllSpriteShifts.BOGEY_BELT.getTarget()
                    .getV0();

            float scroll = BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle;
            scroll = scroll - Mth.floor(scroll);
            scroll = scroll * spriteSize * 0.5f;

            CachedBuffers.partial(BogiePartials.EXTRA_LARGE_8P_BELTS, Blocks.AIR.defaultBlockState())
                    .scale(1 - 1 / 512f)
                    .light(light)
                    .overlay(overlay)
                    .shiftUVScrolling(AllSpriteShifts.BOGEY_BELT, scroll)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.EXTRA_LARGE_8C_FRAME, Blocks.AIR.defaultBlockState())
                    .scale(1 - 1 / 512f)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.EXTRA_LARGE_8C_R_PIN, Blocks.AIR.defaultBlockState())
                    .translate(0, 1.25f, 0)
                    .rotateXDegrees((wheelAngle))
                    .translate(0, .375f, 0)
                    .rotateXDegrees(-(wheelAngle))
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.EXTRA_LARGE_8C_L_PIN, Blocks.AIR.defaultBlockState())
                    .translate(0, 1.25f, 0)
                    .rotateXDegrees((wheelAngle + 90))
                    .translate(0, .375f, 0)
                    .rotateXDegrees(-(wheelAngle + 90))
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.EXTRA_LARGE_8C_R_SLIDER, Blocks.AIR.defaultBlockState())
                    .translate(0, 0, .375f * Math.sin(AngleHelper.rad(wheelAngle)))
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.EXTRA_LARGE_8C_L_SLIDER, Blocks.AIR.defaultBlockState())
                    .translate(0, 0, .375f * Math.sin(AngleHelper.rad(wheelAngle+90)))
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            for (int i : Iterate.positiveAndNegative) {
                wheels.translate(0,1.25f, i*3.375f)
                        .rotateXDegrees(wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                wheels_semi.translate(0,1.25f, i*1.125f)
                        .rotateXDegrees(wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft3.translate(-.5f, .25f, (i*4.375f)-.5f)
                        .center()
                        .rotateXDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);
            }

            for (int j = -1; j < 7; j++) {
                shaft1.translate(-.5f, .25f, (j*1.125f)-3.3125f)
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
