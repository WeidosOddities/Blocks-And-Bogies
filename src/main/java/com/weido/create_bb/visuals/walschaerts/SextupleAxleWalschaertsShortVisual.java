package com.weido.create_bb.visuals.walschaerts;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.processing.burner.ScrollTransformedInstance;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import com.weido.create_bb.data.math.RodCalculations;
import com.weido.create_bb.data.math.RodRenderer;
import com.weido.create_bb.data.rotation.BlocksBogiesBogieVisual;
import com.weido.create_bb.registry.BogiePartials;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.weido.create_bb.data.Constants.*;

public class SextupleAxleWalschaertsShortVisual extends BlocksBogiesBogieVisual {
    public SextupleAxleWalschaertsShortVisual(VisualizationContext ctx, float partialTick, boolean inContraption) { }
    @Override
    public void update(boolean forwards, float wheelAngle, PoseStack poseStack) { }
    @Override
    public void hide() { }
    @Override
    public void updateLight(int packedLight) { }
    @Override
    public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) { }
    @Override
    public void delete() { }

    public static class SextupleAxleLargeWalschaertsShort extends SextupleAxleWalschaertsShortVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance r_c_rod;
        private final TransformedInstance l_c_rod;
        private final TransformedInstance r_m_rod;
        private final TransformedInstance l_m_rod;
        private final TransformedInstance r_p_rod;
        private final TransformedInstance l_p_rod;
        private final TransformedInstance r_r_rod;
        private final TransformedInstance l_r_rod;
        private final TransformedInstance r_e_link;
        private final TransformedInstance l_e_link;
        private final TransformedInstance r_e_rod;
        private final TransformedInstance l_e_rod;
        private final TransformedInstance r_c_lever;
        private final TransformedInstance l_c_lever;
        private final TransformedInstance r_u_link;
        private final TransformedInstance l_u_link;
        private final TransformedInstance r_v_stem;
        private final TransformedInstance l_v_stem;
        private final TransformedInstance e_crank;
        private final TransformedInstance wheel1;
        private final TransformedInstance wheel2;
        private final TransformedInstance wheel3;
        private final TransformedInstance wheel4;
        private final TransformedInstance wheel5;
        private final TransformedInstance wheel6;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;
        private final TransformedInstance shaft5;
        private final TransformedInstance shaft6;
        private final TransformedInstance shaft7;
        private final TransformedInstance shaft8;

        public SextupleAxleLargeWalschaertsShort(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.LARGE_12LW_BELTS))
                    .createInstance();

            r_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_R_C_ROD))
                    .createInstance();
            l_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_L_C_ROD))
                    .createInstance();

            r_m_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_R_M_ROD))
                    .createInstance();
            l_m_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_L_M_ROD))
                    .createInstance();

            r_p_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_R_P_ROD_WALSCHAERTS))
                    .createInstance();
            l_p_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_L_P_ROD_WALSCHAERTS))
                    .createInstance();

            r_r_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_R_R_ROD))
                    .createInstance();
            l_r_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_L_R_ROD))
                    .createInstance();

            r_e_link = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_R_E_LINK))
                    .createInstance();
            l_e_link = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_L_E_LINK))
                    .createInstance();

            r_e_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_R_E_ROD))
                    .createInstance();
            l_e_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_12SW_L_E_ROD))
                    .createInstance();

            r_c_lever = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_R_C_LEVER))
                    .createInstance();
            l_c_lever = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_L_C_LEVER))
                    .createInstance();

            r_u_link = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_R_U_LINK))
                    .createInstance();
            l_u_link = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_L_U_LINK))
                    .createInstance();

            r_v_stem = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_R_V_STEM))
                    .createInstance();
            l_v_stem = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_L_V_STEM))
                    .createInstance();

            e_crank = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_SHARED_ECCENTRIC_CRANK))
                    .createInstance();

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_SHARED_WHEELS));

            var wheelBlindInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_SHARED_WHEELS_BLIND));

            var wheelSemiBlindInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_SHARED_WHEELS_SEMI_BLIND));

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            wheel1 = wheelInstancer.createInstance();
            wheel2 = wheelInstancer.createInstance();
            wheel3 = wheelSemiBlindInstancer.createInstance();
            wheel4 = wheelSemiBlindInstancer.createInstance();
            wheel5 = wheelBlindInstancer.createInstance();
            wheel6 = wheelBlindInstancer.createInstance();

            belt.setSpriteShift(AllSpriteShifts.BOGEY_BELT);

            shaft1 = shaftInstancer.createInstance();
            shaft2 = shaftInstancer.createInstance();
            shaft3 = shaftInstancer.createInstance();
            shaft4 = shaftInstancer.createInstance();
            shaft5 = shaftInstancer.createInstance();
            shaft6 = shaftInstancer.createInstance();
            shaft7 = shaftInstancer.createInstance();
            shaft8 = shaftInstancer.createInstance();
        }

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

        @Override
        public void update(boolean forwards, float wheelAngle, PoseStack poseStack) {
            super.update(forwards, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .setChanged();


            for (int i = 0; i < 2; i++) {
                RodRenderer.VisualResults visualResults = new RodRenderer.VisualResults(i, wheelAngle, forwards, poseStack, params);

                RodRenderer.visualizeConnectingRod(i == 0 ? r_c_rod : l_c_rod, visualResults);
                RodRenderer.visualizeMainRod(i == 0 ? r_m_rod : l_m_rod, visualResults);
                RodRenderer.visualizePistonRod(i == 0 ? r_p_rod : l_p_rod, visualResults);
                RodRenderer.visualizeRadiusRod(i == 0 ? r_r_rod : l_r_rod, visualResults);
                RodRenderer.visualizeExpansionLink(i == 0 ? r_e_link : l_e_link, visualResults);
                RodRenderer.visualizeEccentricRod(i == 0 ? r_e_rod : l_e_rod, visualResults);
                RodRenderer.visualizeCombinationLever(i == 0 ? r_c_lever : l_c_lever, visualResults);
                RodRenderer.visualizeUnionLink(i == 0 ?  r_u_link : l_u_link, visualResults);
                RodRenderer.visualizeValveStem(i == 0 ?  r_v_stem : l_v_stem, visualResults);
            }

            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .setChanged();

            wheel1.setTransform(poseStack)
                    .translate(0, 1, -4.375f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel2.setTransform(poseStack)
                    .translate(0, 1, 4.375f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel3.setTransform(poseStack)
                    .translate(0, 1, -2.625f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel4.setTransform(poseStack)
                    .translate(0, 1, 2.625f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel5.setTransform(poseStack)
                    .translate(0, 1, .875)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel6.setTransform(poseStack)
                    .translate(0, 1, -.875)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            e_crank.setTransform(poseStack)
                    .translate(0, 1, forwards ? LARGE_12S_OFFSET : -LARGE_12S_OFFSET)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, -5.875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, 4.875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, -5.5f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, 4.5f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft5.setTransform(poseStack)
                    .translate(-.5f, .25f, -4f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft6.setTransform(poseStack)
                    .translate(-.5f, .25f, 3)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft7.setTransform(poseStack)
                    .translate(-.5f, .25f, -2.25f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft8.setTransform(poseStack)
                    .translate(-.5f, .25f, 1.25f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();
        }

        @Override
        public void hide() {
            super.hide();
            frame.setZeroTransform().setChanged();
            belt.setZeroTransform().setChanged();
            l_c_rod.setZeroTransform().setChanged();
            r_c_rod.setZeroTransform().setChanged();
            r_m_rod.setZeroTransform().setChanged();
            l_m_rod.setZeroTransform().setChanged();
            r_p_rod.setZeroTransform().setChanged();
            l_p_rod.setZeroTransform().setChanged();
            r_r_rod.setZeroTransform().setChanged();
            l_r_rod.setZeroTransform().setChanged();
            r_e_link.setZeroTransform().setChanged();
            l_e_link.setZeroTransform().setChanged();
            r_e_rod.setZeroTransform().setChanged();
            l_e_rod.setZeroTransform().setChanged();
            r_c_lever.setZeroTransform().setChanged();
            l_c_lever.setZeroTransform().setChanged();
            r_u_link.setZeroTransform().setChanged();
            l_u_link.setZeroTransform().setChanged();
            r_v_stem.setZeroTransform().setChanged();
            l_v_stem.setZeroTransform().setChanged();
            e_crank.setZeroTransform().setChanged();
            wheel1.setZeroTransform().setChanged();
            wheel2.setZeroTransform().setChanged();
            wheel3.setZeroTransform().setChanged();
            wheel4.setZeroTransform().setChanged();
            wheel5.setZeroTransform().setChanged();
            wheel6.setZeroTransform().setChanged();
            shaft1.setZeroTransform().setChanged();
            shaft2.setZeroTransform().setChanged();
            shaft3.setZeroTransform().setChanged();
            shaft4.setZeroTransform().setChanged();
            shaft5.setZeroTransform().setChanged();
            shaft6.setZeroTransform().setChanged();
            shaft7.setZeroTransform().setChanged();
            shaft8.setZeroTransform().setChanged();
        }

        @Override
        public void updateLight(int packedLight) {
            super.updateLight(packedLight);
            frame.light(packedLight).setChanged();
            belt.light(packedLight).setChanged();
            l_c_rod.light(packedLight).setChanged();
            r_c_rod.light(packedLight).setChanged();
            r_m_rod.light(packedLight).setChanged();
            l_m_rod.light(packedLight).setChanged();
            r_p_rod.light(packedLight).setChanged();
            l_p_rod.light(packedLight).setChanged();
            r_r_rod.light(packedLight).setChanged();
            l_r_rod.light(packedLight).setChanged();
            r_e_link.light(packedLight).setChanged();
            l_e_link.light(packedLight).setChanged();
            r_e_rod.light(packedLight).setChanged();
            l_e_rod.light(packedLight).setChanged();
            r_c_lever.light(packedLight).setChanged();
            l_c_lever.light(packedLight).setChanged();
            r_u_link.light(packedLight).setChanged();
            l_u_link.light(packedLight).setChanged();
            r_v_stem.light(packedLight).setChanged();
            l_v_stem.light(packedLight).setChanged();
            e_crank.light(packedLight).setChanged();
            wheel1.light(packedLight).setChanged();
            wheel2.light(packedLight).setChanged();
            wheel3.light(packedLight).setChanged();
            wheel4.light(packedLight).setChanged();
            wheel5.light(packedLight).setChanged();
            wheel6.light(packedLight).setChanged();
            shaft1.light(packedLight).setChanged();
            shaft2.light(packedLight).setChanged();
            shaft3.light(packedLight).setChanged();
            shaft4.light(packedLight).setChanged();
            shaft5.light(packedLight).setChanged();
            shaft6.light(packedLight).setChanged();
            shaft7.light(packedLight).setChanged();
            shaft8.light(packedLight).setChanged();
        }

        @Override
        public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(frame);
            consumer.accept(belt);
            consumer.accept(l_c_rod);
            consumer.accept(r_c_rod);
            consumer.accept(r_m_rod);
            consumer.accept(l_m_rod);
            consumer.accept(r_p_rod);
            consumer.accept(l_p_rod);
            consumer.accept(r_r_rod);
            consumer.accept(l_r_rod);
            consumer.accept(r_e_link);
            consumer.accept(l_e_link);
            consumer.accept(r_e_rod);
            consumer.accept(l_e_rod);
            consumer.accept(r_c_lever);
            consumer.accept(l_c_lever);
            consumer.accept(r_u_link);
            consumer.accept(l_u_link);
            consumer.accept(r_v_stem);
            consumer.accept(l_v_stem);
            consumer.accept(e_crank);
            consumer.accept(wheel1);
            consumer.accept(wheel2);
            consumer.accept(wheel3);
            consumer.accept(wheel4);
            consumer.accept(wheel5);
            consumer.accept(wheel6);
            consumer.accept(shaft1);
            consumer.accept(shaft2);
            consumer.accept(shaft3);
            consumer.accept(shaft4);
            consumer.accept(shaft5);
            consumer.accept(shaft6);
            consumer.accept(shaft7);
            consumer.accept(shaft8);
        }

        @Override
        public void delete() {
            super.delete();
            frame.delete();
            belt.delete();
            l_c_rod.delete();
            r_c_rod.delete();
            r_m_rod.delete();
            l_m_rod.delete();
            r_p_rod.delete();
            l_p_rod.delete();
            r_r_rod.delete();
            l_r_rod.delete();
            r_e_link.delete();
            l_e_link.delete();
            r_e_rod.delete();
            l_e_rod.delete();
            r_c_lever.delete();
            l_c_lever.delete();
            r_u_link.delete();
            l_u_link.delete();
            r_v_stem.delete();
            l_v_stem.delete();
            e_crank.delete();
            wheel1.delete();
            wheel2.delete();
            wheel3.delete();
            wheel4.delete();
            wheel5.delete();
            wheel6.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            shaft4.delete();
            shaft5.delete();
            shaft6.delete();
            shaft7.delete();
            shaft8.delete();
        }
    }
}
