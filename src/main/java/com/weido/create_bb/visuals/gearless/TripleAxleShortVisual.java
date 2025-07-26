package com.weido.create_bb.visuals.gearless;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.processing.burner.ScrollTransformedInstance;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import com.weido.create_bb.data.rotation.BlocksBogiesBogieVisual;
import com.weido.create_bb.registry.BogiePartials;
import com.weido.create_bb.data.math.RodCalculations;
import com.weido.create_bb.data.math.RodRenderer;
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

public class TripleAxleShortVisual extends BlocksBogiesBogieVisual {
    public TripleAxleShortVisual(VisualizationContext ctx, float partialTick, boolean inContraption) { }
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

    public static class TripleAxleLargeShort extends TripleAxleShortVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance r_c_rod;
        private final TransformedInstance l_c_rod;
        private final TransformedInstance r_m_rod;
        private final TransformedInstance l_m_rod;
        private final TransformedInstance r_p_rod;
        private final TransformedInstance l_p_rod;
        private final TransformedInstance wheel1;
        private final TransformedInstance wheel2;
        private final TransformedInstance wheel3;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;
        private final TransformedInstance shaft5;
        private final TransformedInstance shaft6;

        public TripleAxleLargeShort(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_6S_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.LARGE_6P_BELTS))
                    .createInstance();

            r_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_6S_R_C_ROD))
                    .createInstance();
            l_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_6S_L_C_ROD))
                    .createInstance();

            r_m_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_6SW_R_M_ROD))
                    .createInstance();
            l_m_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_6SW_L_M_ROD))
                    .createInstance();

            r_p_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_R_P_ROD_GEARLESS))
                    .createInstance();
            l_p_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_L_P_ROD_GEARLESS))
                    .createInstance();

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_SHARED_WHEELS));

            var wheelSemiBlindInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_SHARED_WHEELS_SEMI_BLIND));

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            wheel1 = wheelInstancer.createInstance();
            wheel2 = wheelInstancer.createInstance();
            wheel3 = wheelSemiBlindInstancer.createInstance();

            belt.setSpriteShift(AllSpriteShifts.BOGEY_BELT);

            shaft1 = shaftInstancer.createInstance();
            shaft2 = shaftInstancer.createInstance();

            shaft3 = shaftInstancer.createInstance();
            shaft4 = shaftInstancer.createInstance();
            shaft5 = shaftInstancer.createInstance();
            shaft6 = shaftInstancer.createInstance();
        }

        RodCalculations.WalschaertsParameters params = new RodCalculations.WalschaertsParameters(
                LARGE_6S_ECCENTRIC_ROD,
                LARGE_EXPANSION_LINK,
                LARGE_ECCENTRIC_CRANK_RADIUS,
                LARGE_6S_EXPANSION_LINK_X,
                LARGE_EXPANSION_LINK_Y,
                LARGE_E_LINK_R_BAR_CONNECTION,
                LARGE_MAIN_CRANK_RADIUS,
                LARGE_6S_MAIN_ROD_LENGTH,
                LARGE_6S_RADIUS_ROD,
                LARGE_COMBINATION_LEVER,
                LARGE_COMBINATION_UPPER,
                LARGE_COMBINATION_LOWER,
                LARGE_UNION_LINK,
                LARGE_DROP_LINK,
                LARGE_VALVE_Y,
                LARGE_6S_PISTON_DISTANCE,
                LARGE_CENTER_HEIGHT,
                LARGE_6S_OFFSET
        );

        @Override
        public void update(boolean forwards, float wheelAngle, PoseStack poseStack) {
            super.update(forwards, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            for (int i = 0; i < 2; i++) {
                RodRenderer.VisualResults visualResults = new RodRenderer.VisualResults(i, wheelAngle, forwards, poseStack, params);

                RodRenderer.visualizeConnectingRod(i == 0 ? r_c_rod : l_c_rod, visualResults);
                RodRenderer.visualizeMainRod(i == 0 ? r_m_rod : l_m_rod, visualResults);
                RodRenderer.visualizePistonRod(i == 0 ? r_p_rod : l_p_rod, visualResults);
            }

            wheel1.setTransform(poseStack)
                    .translate(0, 1, -1.6875f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel2.setTransform(poseStack)
                    .translate(0, 1, 1.6875f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel3.setTransform(poseStack)
                    .translate(0, 1, 0)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, -3.1875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, 2.1875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, 1.8125f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, -2.8125f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft5.setTransform(poseStack)
                    .translate(-.5f, .25f, 0.375f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft6.setTransform(poseStack)
                    .translate(-.5f, .25f, -1.375f)
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
            wheel1.setZeroTransform().setChanged();
            wheel2.setZeroTransform().setChanged();
            wheel3.setZeroTransform().setChanged();
            shaft1.setZeroTransform().setChanged();
            shaft2.setZeroTransform().setChanged();
            shaft3.setZeroTransform().setChanged();
            shaft4.setZeroTransform().setChanged();
            shaft5.setZeroTransform().setChanged();
            shaft6.setZeroTransform().setChanged();
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
            wheel1.light(packedLight).setChanged();
            wheel2.light(packedLight).setChanged();
            wheel3.light(packedLight).setChanged();
            shaft1.light(packedLight).setChanged();
            shaft2.light(packedLight).setChanged();
            shaft3.light(packedLight).setChanged();
            shaft4.light(packedLight).setChanged();
            shaft5.light(packedLight).setChanged();
            shaft6.light(packedLight).setChanged();
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
            consumer.accept(wheel1);
            consumer.accept(wheel2);
            consumer.accept(wheel3);
            consumer.accept(shaft1);
            consumer.accept(shaft2);
            consumer.accept(shaft3);
            consumer.accept(shaft4);
            consumer.accept(shaft5);
            consumer.accept(shaft6);
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
            wheel1.delete();
            wheel2.delete();
            wheel3.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            shaft4.delete();
            shaft5.delete();
            shaft6.delete();
        }
    }

    public static class TripleAxleExtraLargeShort extends TripleAxleShortVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance r_c_rod;
        private final TransformedInstance l_c_rod;
        private final TransformedInstance r_m_rod;
        private final TransformedInstance l_m_rod;
        private final TransformedInstance r_p_rod;
        private final TransformedInstance l_p_rod;
        private final TransformedInstance wheel1;
        private final TransformedInstance wheel2;
        private final TransformedInstance wheel3;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;
        private final TransformedInstance shaft5;
        private final TransformedInstance shaft6;
        private final TransformedInstance shaft7;
        private final TransformedInstance shaft8;

        public TripleAxleExtraLargeShort(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_6S_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_6P_BELTS))
                    .createInstance();

            r_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_6S_R_C_ROD))
                    .createInstance();
            l_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_6S_L_C_ROD))
                    .createInstance();

            r_m_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_6SW_R_M_ROD))
                    .createInstance();
            l_m_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_6SW_L_M_ROD))
                    .createInstance();

            r_p_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_R_P_ROD_GEARLESS))
                    .createInstance();
            l_p_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_L_P_ROD_GEARLESS))
                    .createInstance();

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS));

            var wheelSemiBlindInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS_SEMI_BLIND));

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            wheel1 = wheelInstancer.createInstance();
            wheel2 = wheelInstancer.createInstance();
            wheel3 = wheelSemiBlindInstancer.createInstance();

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
                EXTRA_LARGE_6S_ECCENTRIC_ROD,
                EXTRA_LARGE_EXPANSION_LINK,
                EXTRA_LARGE_ECCENTRIC_CRANK_RADIUS,
                EXTRA_LARGE_6S_EXPANSION_LINK_X,
                EXTRA_LARGE_EXPANSION_LINK_Y,
                EXTRA_LARGE_E_LINK_R_BAR_CONNECTION,
                EXTRA_LARGE_MAIN_CRANK_RADIUS,
                EXTRA_LARGE_6S_MAIN_ROD_LENGTH,
                EXTRA_LARGE_6S_RADIUS_ROD,
                EXTRA_LARGE_COMBINATION_LEVER,
                EXTRA_LARGE_COMBINATION_UPPER,
                EXTRA_LARGE_COMBINATION_LOWER,
                EXTRA_LARGE_UNION_LINK,
                EXTRA_LARGE_DROP_LINK,
                EXTRA_LARGE_VALVE_Y,
                EXTRA_LARGE_6S_PISTON_DISTANCE,
                EXTRA_LARGE_CENTER_HEIGHT,
                EXTRA_LARGE_6S_OFFSET
        );

        @Override
        public void update(boolean forwards, float wheelAngle, PoseStack poseStack) {
            super.update(forwards, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .setChanged();

            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .setChanged();

            for (int i = 0; i < 2; i++) {
                RodRenderer.VisualResults visualResults = new RodRenderer.VisualResults(i, wheelAngle, forwards, poseStack, params);

                RodRenderer.visualizeConnectingRod(i == 0 ? r_c_rod : l_c_rod, visualResults);
                RodRenderer.visualizeMainRod(i == 0 ? r_m_rod : l_m_rod, visualResults);
                RodRenderer.visualizePistonRod(i == 0 ? r_p_rod : l_p_rod, visualResults);
            }

            wheel1.setTransform(poseStack)
                    .translate(0, 1.25f, -2.25f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel2.setTransform(poseStack)
                    .translate(0, 1.25f, 2.25f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            wheel3.setTransform(poseStack)
                    .translate(0, 1.25f, 0)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .rotateXDegrees(forwards ? wheelAngle : -wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, -3.75f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, 2.75f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, 0.0625f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, -1.0625f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft5.setTransform(poseStack)
                    .translate(-.5f, .25f, 1.1875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft6.setTransform(poseStack)
                    .translate(-.5f, .25f, -2.1875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft7.setTransform(poseStack)
                    .translate(-.5f, .25f, 2.3125f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft8.setTransform(poseStack)
                    .translate(-.5f, .25f, -3.3125f)
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
            wheel1.setZeroTransform().setChanged();
            wheel2.setZeroTransform().setChanged();
            wheel3.setZeroTransform().setChanged();
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
            wheel1.light(packedLight).setChanged();
            wheel2.light(packedLight).setChanged();
            wheel3.light(packedLight).setChanged();
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
            consumer.accept(wheel1);
            consumer.accept(wheel2);
            consumer.accept(wheel3);
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
            wheel1.delete();
            wheel2.delete();
            wheel3.delete();
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
