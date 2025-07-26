package com.weido.create_bb.visuals.standard;

import com.weido.create_bb.data.rotation.BlocksBogiesBogieVisual;
import org.jetbrains.annotations.Nullable;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import com.weido.create_bb.registry.BogiePartials;
import java.util.function.Consumer;

public class SingleAxleBogieVisual extends BlocksBogiesBogieVisual {
    public SingleAxleBogieVisual(VisualizationContext ctx, float partialTick, boolean inContraption) { }
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

    public static class SingleAxleSmallOffset extends SingleAxleBogieVisual {
        public final TransformedInstance frame;
        public final TransformedInstance shaft;
        public final TransformedInstance wheel;

        public SingleAxleSmallOffset(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.SMALL_OFFSET_2_FRAME))
                    .createInstance();

            shaft = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT))
                    .createInstance();

            wheel = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SMALL_BOGEY_WHEELS))
                    .createInstance();
        }

        @Override
        public void update(boolean forwards, float wheelAngle, PoseStack poseStack) {
            super.update(forwards, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .rotateYDegrees(forwards ? 0 : 180)
                    .setChanged();

            shaft.setTransform(poseStack)
                    .translate(-.5f, .25f, forwards ? -1 : 0)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            wheel.setTransform(poseStack)
                    .translate(0, .75f, forwards ? -1 : 1)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();
        }

        @Override
        public void hide() {
            super.hide();
            frame.setZeroTransform().setChanged();
            shaft.setZeroTransform().setChanged();
            wheel.setZeroTransform().setChanged();
        }

        @Override
        public void updateLight(int packedLight) {
            frame.light(packedLight).setChanged();
            shaft.light(packedLight).setChanged();
            wheel.light(packedLight).setChanged();
        }
        @Override
        public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(frame);
            consumer.accept(shaft);
            consumer.accept(wheel);
        }

        @Override
        public void delete() {
            super.delete();
            frame.delete();
            shaft.delete();
            wheel.delete();
        }
    }

    public static class SingleAxleSmallBogie extends SingleAxleBogieVisual {
        public final TransformedInstance frame;
        public final TransformedInstance shaft1;
        public final TransformedInstance shaft2;
        public final TransformedInstance wheel;

        public SingleAxleSmallBogie(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.SMALL_BOGIE_2_FRAME))
                    .createInstance();

            shaft1 = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT))
                    .createInstance();

            shaft2 = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT))
                    .createInstance();

            wheel = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SMALL_BOGEY_WHEELS))
                    .createInstance();
        }

        @Override
        public void update(boolean forwards, float wheelAngle, PoseStack poseStack) {
            super.update(forwards, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
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

            wheel.setTransform(poseStack)
                    .translate(0, .75f, 0)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();
        }

        @Override
        public void hide() {
            super.hide();
            frame.setZeroTransform().setChanged();
            shaft1.setZeroTransform().setChanged();
            shaft2.setZeroTransform().setChanged();
            wheel.setZeroTransform().setChanged();
        }

        @Override
        public void updateLight(int packedLight) {
            frame.light(packedLight).setChanged();
            shaft1.light(packedLight).setChanged();
            shaft2.light(packedLight).setChanged();
            wheel.light(packedLight).setChanged();
        }
        @Override
        public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(frame);
            consumer.accept(shaft1);
            consumer.accept(shaft2);
            consumer.accept(wheel);
        }

        @Override
        public void delete() {
            super.delete();
            frame.delete();
            shaft1.delete();
            shaft2.delete();
            wheel.delete();
        }
    }
}
