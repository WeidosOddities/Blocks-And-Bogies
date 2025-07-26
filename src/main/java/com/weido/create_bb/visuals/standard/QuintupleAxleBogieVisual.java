package com.weido.create_bb.visuals.standard;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.trains.bogey.BogeyVisual;
import com.weido.create_bb.registry.BogiePartials;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class QuintupleAxleBogieVisual implements BogeyVisual {
    public QuintupleAxleBogieVisual(VisualizationContext ctx, float partialTick, boolean inContraption) { }
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

    public static class SmallQuintupleAxleBogie extends QuintupleAxleBogieVisual {
        public final TransformedInstance frame;
        public final TransformedInstance shaft1;
        public final TransformedInstance shaft2;
        public final TransformedInstance shaft3;
        public final TransformedInstance shaft4;
        public final TransformedInstance wheel1;
        public final TransformedInstance wheel2;
        public final TransformedInstance wheel3;
        public final TransformedInstance wheel4;
        public final TransformedInstance wheel5;

        public SmallQuintupleAxleBogie(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.SMALL_BOGIE_10_FRAME))
                    .createInstance();

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SMALL_BOGEY_WHEELS));

            shaft1 = shaftInstancer.createInstance();
            shaft2 = shaftInstancer.createInstance();

            shaft3 = shaftInstancer.createInstance();
            shaft4 = shaftInstancer.createInstance();

            wheel1 = wheelInstancer.createInstance();
            wheel2 = wheelInstancer.createInstance();
            wheel3 = wheelInstancer.createInstance();
            wheel4 = wheelInstancer.createInstance();
            wheel5 = wheelInstancer.createInstance();
        }

        @Override
        public void update(CompoundTag bogeyData, float wheelAngle, PoseStack poseStack) {
            frame.setTransform(poseStack)
                    .scale(1-1 / 512f)
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
                    .translate(-.5f, .25f, 1)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, -2)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            wheel1.setTransform(poseStack)
                    .translate(0, .75f, 0)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel2.setTransform(poseStack)
                    .translate(0, .75f, 1)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel3.setTransform(poseStack)
                    .translate(0, .75f, -1)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel4.setTransform(poseStack)
                    .translate(0, .75f, 2)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel5.setTransform(poseStack)
                    .translate(0, .75f, -2)
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
            shaft4.setZeroTransform().setChanged();
            wheel1.setZeroTransform().setChanged();
            wheel2.setZeroTransform().setChanged();
            wheel3.setZeroTransform().setChanged();
            wheel4.setZeroTransform().setChanged();
            wheel5.setZeroTransform().setChanged();
        }

        @Override
        public void updateLight(int packedLight) {
            frame.light(packedLight).setChanged();
            shaft1.light(packedLight).setChanged();
            shaft2.light(packedLight).setChanged();
            shaft3.light(packedLight).setChanged();
            shaft4.light(packedLight).setChanged();
            wheel1.light(packedLight).setChanged();
            wheel2.light(packedLight).setChanged();
            wheel3.light(packedLight).setChanged();
            wheel4.light(packedLight).setChanged();
            wheel5.light(packedLight).setChanged();
        }

        @Override
        public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(frame);
            consumer.accept(shaft1);
            consumer.accept(shaft2);
            consumer.accept(shaft3);
            consumer.accept(shaft4);
            consumer.accept(wheel1);
            consumer.accept(wheel2);
            consumer.accept(wheel2);
            consumer.accept(wheel4);
            consumer.accept(wheel5);
        }

        @Override
        public void delete() {
            super.delete();
            frame.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            shaft4.delete();
            wheel1.delete();
            wheel2.delete();
            wheel3.delete();
            wheel4.delete();
            wheel5.delete();
        }
    }
}
