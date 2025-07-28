package com.weido.create_bb.renderers.walschaerts;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.weido.create_bb.data.math.RodCalculations;
import com.weido.create_bb.data.rotation.BlocksBogiesBogieRenderer;
import com.weido.create_bb.registry.BogiePartials;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;

import static com.weido.create_bb.data.Constants.*;
import static com.weido.create_bb.data.math.RodRenderer.*;

public class SextupleAxleWalschaertsShortRenderer extends BlocksBogiesBogieRenderer {
    @Override public void render(boolean forwards, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) { }

    public static class SextuplesAxleLargeWalschaertsShort extends SextupleAxleWalschaertsShortRenderer {
        @Override
        public void render(boolean forwards, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
            super.render(forwards, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

            VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());

            SuperByteBuffer shaft1 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));
            SuperByteBuffer shaft2 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));
            SuperByteBuffer shaft3 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.X));
            SuperByteBuffer shaft4 = CachedBuffers.block(AllBlocks.SHAFT.getDefaultState().setValue(ShaftBlock.AXIS, Direction.Axis.Z));

            SuperByteBuffer wheels1 = CachedBuffers.partial(BogiePartials.LARGE_SHARED_WHEELS, Blocks.AIR.defaultBlockState());
            SuperByteBuffer wheels2 = CachedBuffers.partial(BogiePartials.LARGE_SHARED_WHEELS_BLIND, Blocks.AIR.defaultBlockState());
            SuperByteBuffer wheels3 = CachedBuffers.partial(BogiePartials.LARGE_SHARED_WHEELS_SEMI_BLIND, Blocks.AIR.defaultBlockState());


            float spriteSize = AllSpriteShifts.BOGEY_BELT.getTarget()
                    .getV1()
                    - AllSpriteShifts.BOGEY_BELT.getTarget()
                    .getV0();

            float scroll = BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle;
            scroll = scroll - Mth.floor(scroll);
            scroll = scroll * spriteSize * 0.5f;

            RodCalculations.WalschaertsParameters params = new RodCalculations.WalschaertsParameters(
                    LARGE_12S_ECCENTRIC_ROD,
                    LARGE_EXPANSION_LINK,
                    LARGE_ECCENTRIC_CRANK_RADIUS,
                    LARGE_12S_EXPANSION_LINK_X,
                    LARGE_EXPANSION_LINK_Y,
                    LARGE_E_LINK_R_BAR_CONNECTION,
                    LARGE_MAIN_CRANK_RADIUS,
                    LARGE_12S_MAIN_ROD_LENGTH,
                    LARGE_12S_RADIUS_ROD,
                    LARGE_COMBINATION_LEVER,
                    LARGE_COMBINATION_UPPER,
                    LARGE_COMBINATION_LOWER,
                    LARGE_UNION_LINK,
                    LARGE_DROP_LINK,
                    LARGE_VALVE_Y,
                    LARGE_12S_PISTON_DISTANCE,
                    LARGE_CENTER_HEIGHT,
                    LARGE_12S_OFFSET
            );

            CachedBuffers.partial(BogiePartials.LARGE_12LW_BELTS, Blocks.AIR.defaultBlockState())
                    .scale(1-1 / 512f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .light(light)
                    .overlay(overlay)
                    .shiftUVScrolling(AllSpriteShifts.BOGEY_BELT, scroll)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.LARGE_12SW_FRAME, Blocks.AIR.defaultBlockState())
                    .scale(1-1 / 512f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            CachedBuffers.partial(BogiePartials.LARGE_SHARED_ECCENTRIC_CRANK, Blocks.AIR.defaultBlockState())
                    .translate(0, LARGE_CENTER_HEIGHT, forwards ? LARGE_12S_OFFSET : -LARGE_12S_OFFSET)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .light(light)
                    .overlay(overlay)
                    .renderInto(poseStack, buffer);

            for (int i = 0; i < 2; i++) {
                RenderingResults renderingResults = new RenderingResults(i, light, overlay, wheelAngle, forwards, poseStack, buffer, params);

                renderConnectingRod(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_12SW_R_C_ROD : BogiePartials.LARGE_12SW_L_C_ROD, Blocks.AIR.defaultBlockState()), renderingResults);

                renderMainRod(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_12SW_R_M_ROD : BogiePartials.LARGE_12SW_L_M_ROD, Blocks.AIR.defaultBlockState()), renderingResults);

                renderPistonRod(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_R_P_ROD_WALSCHAERTS : BogiePartials.LARGE_L_P_ROD_WALSCHAERTS, Blocks.AIR.defaultBlockState()), renderingResults);

                renderRadiusRod(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_12SW_R_R_ROD : BogiePartials.LARGE_12SW_L_R_ROD, Blocks.AIR.defaultBlockState()), renderingResults);

                renderExpansionLink(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_R_E_LINK : BogiePartials.LARGE_L_E_LINK, Blocks.AIR.defaultBlockState()), renderingResults);

                renderEccentricRod(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_12SW_R_E_ROD : BogiePartials.LARGE_12SW_L_E_ROD, Blocks.AIR.defaultBlockState()), renderingResults);

                renderCombinationLever(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_R_C_LEVER : BogiePartials.LARGE_L_C_LEVER, Blocks.AIR.defaultBlockState()), renderingResults);

                renderUnionLink(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_R_U_LINK : BogiePartials.LARGE_L_U_LINK, Blocks.AIR.defaultBlockState()), renderingResults);

                renderValveStem(CachedBuffers.partial(i == 0 ? BogiePartials.LARGE_R_V_STEM : BogiePartials.LARGE_L_V_STEM, Blocks.AIR.defaultBlockState()), renderingResults);
            }

            for(int i : Iterate.positiveAndNegative) {
                wheels1.translate(0, 1, i*4.375f)
                        .rotateYDegrees(forwards ? 0 : 180)
                        .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                wheels3.translate(0, 1, i*2.625f)
                        .rotateYDegrees(forwards ? 0 : 180)
                        .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                wheels2.translate(0, 1, i*.875f)
                        .rotateYDegrees(forwards ? 0 : 180)
                        .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft2.translate(-.5f, .25f, (i*5)-.5f)
                        .center()
                        .rotateZDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft3.translate(-.5f, .25f, (i*5.375f)-.5f)
                        .center()
                        .rotateXDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft1.translate(-.5f, .25f, (i*3.5f)-.5f)
                        .center()
                        .rotateZDegrees(wheelAngle)
                        .uncenter()
                        .light(light)
                        .overlay(overlay)
                        .renderInto(poseStack, buffer);

                shaft4.translate(-.5f, .25f, (i*1.75f)-.5f)
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
