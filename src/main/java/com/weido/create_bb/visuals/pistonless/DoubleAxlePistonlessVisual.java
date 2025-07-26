package com.weido.create_bb.visuals.pistonless;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.processing.burner.ScrollTransformedInstance;
import com.simibubi.create.content.trains.bogey.BogeyVisual;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;
import com.weido.create_bb.registry.BogiePartials;
import static com.weido.create_bb.data.Constants.*;

public class DoubleAxlePistonlessVisual implements BogeyVisual {
    public DoubleAxlePistonlessVisual(VisualizationContext ctx, float partialTick, boolean inContraption) { }
    @Override
    public void update(CompoundTag bogieData, float wheelAngle, PoseStack poseStack) { }
    @Override
    public void hide() { }
    @Override
    public void updateLight(int packedLight) { }
    @Override
    public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) { }
    @Override
    public void delete() { }

    public static class DoubleAxleLargePistonless extends DoubleAxlePistonlessVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance r_c_rod;
        private final TransformedInstance l_c_rod;
        private final TransformedInstance wheel1;
        private final TransformedInstance wheel2;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;

        public DoubleAxleLargePistonless(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_4P_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.LARGE_4P_BELTS))
                    .createInstance();

            r_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_4P_R_C_ROD))
                    .createInstance();

            l_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_4P_L_C_ROD))
                    .createInstance();

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_SHARED_WHEELS));

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            wheel1 = wheelInstancer.createInstance();
            wheel2 = wheelInstancer.createInstance();

            belt.setSpriteShift(AllSpriteShifts.BOGEY_BELT);

            shaft1 = shaftInstancer.createInstance();
            shaft2 = shaftInstancer.createInstance();
            shaft3 = shaftInstancer.createInstance();
            shaft4 = shaftInstancer.createInstance();
        }

        @Override
        public void update(CompoundTag bogieData, float wheelAngle, PoseStack poseStack) {
            super.update(bogieData, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            r_c_rod.setTransform(poseStack)
                    .translate(0, 1, 0)
                    .rotateXDegrees((wheelAngle))
                    .translate(0, .25f, 0)
                    .rotateXDegrees(-(wheelAngle))
                    .setChanged();

            l_c_rod.setTransform(poseStack)
                    .translate(0, 1, 0)
                    .rotateXDegrees((wheelAngle + 90))
                    .translate(0, .25f, 0)
                    .rotateXDegrees(-(wheelAngle + 90))
                    .setChanged();
            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            wheel1.setTransform(poseStack)
                    .translate(0, 1, -0.875f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel2.setTransform(poseStack)
                    .translate(0, 1, 0.875f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, 1)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, -2)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, 1.375f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, -2.375f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
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
            wheel1.setZeroTransform().setChanged();
            wheel2.setZeroTransform().setChanged();
            shaft1.setZeroTransform().setChanged();
            shaft2.setZeroTransform().setChanged();
            shaft3.setZeroTransform().setChanged();
            shaft4.setZeroTransform().setChanged();
        }

        @Override
        public void updateLight(int packedLight) {
            super.updateLight(packedLight);
            frame.light(packedLight).setChanged();
            belt.light(packedLight).setChanged();
            l_c_rod.light(packedLight).setChanged();
            r_c_rod.light(packedLight).setChanged();
            wheel1.light(packedLight).setChanged();
            wheel2.light(packedLight).setChanged();
            shaft1.light(packedLight).setChanged();
            shaft2.light(packedLight).setChanged();
            shaft3.light(packedLight).setChanged();
            shaft4.light(packedLight).setChanged();
        }

        @Override
        public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(frame);
            consumer.accept(belt);
            consumer.accept(l_c_rod);
            consumer.accept(r_c_rod);
            consumer.accept(wheel1);
            consumer.accept(wheel2);
            consumer.accept(shaft1);
            consumer.accept(shaft2);
            consumer.accept(shaft3);
            consumer.accept(shaft4);
        }

        @Override
        public void delete() {
            super.delete();
            frame.delete();
            belt.delete();
            l_c_rod.delete();
            r_c_rod.delete();
            wheel1.delete();
            wheel2.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            shaft4.delete();
        }
    }
    public static class DoubleAxleExtraLargePistonless extends DoubleAxlePistonlessVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance r_c_rod;
        private final TransformedInstance l_c_rod;
        private final TransformedInstance wheel1;
        private final TransformedInstance wheel2;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;
        private final TransformedInstance shaft5;
        private final TransformedInstance shaft6;

        public DoubleAxleExtraLargePistonless(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_4P_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_4P_BELTS))
                    .createInstance();

            r_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_4P_R_C_ROD))
                    .createInstance();

            l_c_rod = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_4P_L_C_ROD))
                    .createInstance();

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS));

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            wheel1 = wheelInstancer.createInstance();
            wheel2 = wheelInstancer.createInstance();

            belt.setSpriteShift(AllSpriteShifts.BOGEY_BELT);

            shaft1 = shaftInstancer.createInstance();
            shaft2 = shaftInstancer.createInstance();
            shaft3 = shaftInstancer.createInstance();
            shaft4 = shaftInstancer.createInstance();
            shaft5 = shaftInstancer.createInstance();
            shaft6 = shaftInstancer.createInstance();
        }

        @Override
        public void update(CompoundTag bogieData, float wheelAngle, PoseStack poseStack) {
            super.update(bogieData, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            r_c_rod.setTransform(poseStack)
                    .translate(0, 1.25f, 0)
                    .rotateXDegrees((wheelAngle))
                    .translate(0, .375f, 0)
                    .rotateXDegrees(-(wheelAngle))
                    .setChanged();

            l_c_rod.setTransform(poseStack)
                    .translate(0, 1.25f, 0)
                    .rotateXDegrees((wheelAngle + 90))
                    .translate(0, .375f, 0)
                    .rotateXDegrees(-(wheelAngle + 90))
                    .setChanged();
            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            wheel1.setTransform(poseStack)
                    .translate(0, 1.25f, -1.125f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel2.setTransform(poseStack)
                    .translate(0, 1.25f, 1.125f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, 0f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, -1f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, 1.25f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, -2.25f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft5.setTransform(poseStack)
                    .translate(-.5f, .25f, 1.625f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft6.setTransform(poseStack)
                    .translate(-.5f, .25f, -2.625f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
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
            wheel1.setZeroTransform().setChanged();
            wheel2.setZeroTransform().setChanged();
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
            wheel1.light(packedLight).setChanged();
            wheel2.light(packedLight).setChanged();
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
            consumer.accept(wheel1);
            consumer.accept(wheel2);
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
            wheel1.delete();
            wheel2.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            shaft4.delete();
            shaft5.delete();
            shaft6.delete();
        }
    }
}
