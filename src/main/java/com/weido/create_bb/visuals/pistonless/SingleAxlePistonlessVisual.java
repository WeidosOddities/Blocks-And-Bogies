package com.weido.create_bb.visuals.pistonless;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.processing.burner.ScrollTransformedInstance;
import com.simibubi.create.content.trains.bogey.BogeyVisual;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import com.weido.create_bb.registry.BogiePartials;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import java.util.function.Consumer;

import static com.weido.create_bb.data.Constants.BELT_RADIUS_IN_UV_SPACE;

public class SingleAxlePistonlessVisual implements BogeyVisual {
    public SingleAxlePistonlessVisual(VisualizationContext ctx, float partialTick, boolean inContraption) { }
    @Override
    public void update(CompoundTag bogeyData, float wheelAngle, PoseStack poseStack) { }
    @Override
    public void hide() { }
    @Override
    public void updateLight(int packedLight) { }
    @Override
    public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) { }
    @Override
    public void delete() { }

    public static class SingleAxleLargePistonless extends SingleAxlePistonlessVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance wheel1;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;

        public SingleAxleLargePistonless(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_2P_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.LARGE_2P_BELTS))
                    .createInstance();

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_SHARED_WHEELS_SINGLE));

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            wheel1 = wheelInstancer.createInstance();

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

            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            wheel1.setTransform(poseStack)
                    .translate(0, 1, 0)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, 0)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, -1)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, 0.5f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, -1.5f)
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
            wheel1.setZeroTransform().setChanged();
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
            wheel1.light(packedLight).setChanged();
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
            consumer.accept(wheel1);
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
            wheel1.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            shaft4.delete();
        }
    }
    public static class SingleAxleExtraLargePistonless extends SingleAxlePistonlessVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance wheel1;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;

        public SingleAxleExtraLargePistonless(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_2P_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_2P_BELTS))
                    .createInstance();

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS_SINGLE));

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            wheel1 = wheelInstancer.createInstance();

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

            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            wheel1.setTransform(poseStack)
                    .translate(0, 1.25f, 0)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, 0)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, -1)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, 0.5f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, -1.5f)
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
            wheel1.setZeroTransform().setChanged();
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
            wheel1.light(packedLight).setChanged();
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
            consumer.accept(wheel1);
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
            wheel1.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            shaft4.delete();
        }
    }
}
