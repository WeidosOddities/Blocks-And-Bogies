package com.weido.create_bb.visuals.trailing;

import org.jetbrains.annotations.Nullable;
import java.util.function.Consumer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.trains.bogey.BogeyVisual;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import com.weido.create_bb.registry.BogiePartials;

public class SingleAxleTrailingVisual implements BogeyVisual {
    public SingleAxleTrailingVisual(VisualizationContext ctx, float partialTick, boolean inContraption) { }
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

    public static class SingleAxleSmallTrailing extends SingleAxleTrailingVisual {
        public final TransformedInstance frame;
        public final TransformedInstance shaft1;
        public final TransformedInstance shaft2;
        public final TransformedInstance shaft3;
        public final TransformedInstance wheel1;

        public SingleAxleSmallTrailing(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.SMALL_TRAILING_2_FRAME))
                    .createInstance();

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.SMALL_SHARED_WHEELS));

            shaft1 = shaftInstancer.createInstance();
            shaft2 = shaftInstancer.createInstance();
            shaft3 = shaftInstancer.createInstance();
            wheel1 = wheelInstancer.createInstance();
        }

        @Override
        public void update(CompoundTag bogeyData, float wheelAngle, PoseStack poseStack) {
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

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, -.5f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            wheel1.setTransform(poseStack)
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
            shaft3.setZeroTransform().setChanged();
            wheel1.setZeroTransform().setChanged();
        }

        @Override
        public void updateLight(int packedLight) {
            frame.light(packedLight).setChanged();
            shaft1.light(packedLight).setChanged();
            shaft2.light(packedLight).setChanged();
            shaft3.light(packedLight).setChanged();
            wheel1.light(packedLight).setChanged();
        }

        @Override
        public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(frame);
            consumer.accept(shaft1);
            consumer.accept(shaft2);
            consumer.accept(shaft3);
            consumer.accept(wheel1);
        }

        @Override
        public void delete() {
            super.delete();
            frame.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            wheel1.delete();
        }
    }
}
