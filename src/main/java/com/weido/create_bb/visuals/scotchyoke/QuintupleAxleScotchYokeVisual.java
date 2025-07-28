package com.weido.create_bb.visuals.scotchyoke;

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
import net.createmod.catnip.math.AngleHelper;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static com.weido.create_bb.data.Constants.BELT_RADIUS_IN_UV_SPACE;

public class QuintupleAxleScotchYokeVisual implements BogeyVisual {
    public QuintupleAxleScotchYokeVisual(VisualizationContext ctx, float partialTick, boolean inContraption) { }
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

    public static class QuintupleAxleLargeScotchYoke extends QuintupleAxleScotchYokeVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance r_pin;
        private final TransformedInstance l_pin;
        private final TransformedInstance r_slider;
        private final TransformedInstance l_slider;
        private final TransformedInstance wheel1;
        private final TransformedInstance wheel2;
        private final TransformedInstance wheel3;
        private final TransformedInstance wheel4;
        private final TransformedInstance wheel5;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;
        private final TransformedInstance shaft5;
        private final TransformedInstance shaft6;
        private final TransformedInstance shaft7;
        private final TransformedInstance shaft8;

        public QuintupleAxleLargeScotchYoke(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_10C_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.LARGE_10P_BELTS))
                    .createInstance();

            r_pin = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_10C_R_PIN))
                    .createInstance();

            l_pin = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_10C_L_PIN))
                    .createInstance();

            r_slider = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_10C_R_SLIDER))
                    .createInstance();

            l_slider = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.LARGE_10C_L_SLIDER))
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
        @Override
        public void update(CompoundTag bogeyData, float wheelAngle, PoseStack poseStack) {
            super.update(bogeyData, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            r_pin.setTransform(poseStack)
                    .translate(0, 1, 0)
                    .rotateXDegrees((wheelAngle))
                    .translate(0, .25f, 0)
                    .rotateXDegrees(-(wheelAngle))
                    .setChanged();

            l_pin.setTransform(poseStack)
                    .translate(0, 1, 0)
                    .rotateXDegrees((wheelAngle + 90))
                    .translate(0, .25f, 0)
                    .rotateXDegrees(-(wheelAngle + 90))
                    .setChanged();

            r_slider.setTransform(poseStack)
                    .translate(0, 0, .25f * Math.sin(AngleHelper.rad(wheelAngle)))
                    .setChanged();

            l_slider.setTransform(poseStack)
                    .translate(0, 0, .25f * Math.sin(AngleHelper.rad(wheelAngle+90)))
                    .setChanged();

            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            wheel1.setTransform(poseStack)
                    .translate(0, 1, -3.375f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel2.setTransform(poseStack)
                    .translate(0, 1, 3.375f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel3.setTransform(poseStack)
                    .translate(0, 1, -1.6875f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel4.setTransform(poseStack)
                    .translate(0, 1, 1.6875f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel5.setTransform(poseStack)
                    .translate(0, 1, 0)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, -4.875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, 3.875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, -4.5f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, 3.5f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft5.setTransform(poseStack)
                    .translate(-.5f, .25f, -3f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft6.setTransform(poseStack)
                    .translate(-.5f, .25f, 2)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft7.setTransform(poseStack)
                    .translate(-.5f, .25f, -1.3125)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft8.setTransform(poseStack)
                    .translate(-.5f, .25f, .3125f)
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
            l_pin.setZeroTransform().setChanged();
            r_pin.setZeroTransform().setChanged();
            l_slider.setZeroTransform().setChanged();
            r_slider.setZeroTransform().setChanged();
            wheel1.setZeroTransform().setChanged();
            wheel2.setZeroTransform().setChanged();
            wheel3.setZeroTransform().setChanged();
            wheel4.setZeroTransform().setChanged();
            wheel5.setZeroTransform().setChanged();
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
            l_pin.light(packedLight).setChanged();
            r_pin.light(packedLight).setChanged();
            l_slider.light(packedLight).setChanged();
            r_slider.light(packedLight).setChanged();
            wheel1.light(packedLight).setChanged();
            wheel2.light(packedLight).setChanged();
            wheel3.light(packedLight).setChanged();
            wheel4.light(packedLight).setChanged();
            wheel5.light(packedLight).setChanged();
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
            consumer.accept(l_pin);
            consumer.accept(r_pin);
            consumer.accept(l_slider);
            consumer.accept(r_slider);
            consumer.accept(wheel1);
            consumer.accept(wheel2);
            consumer.accept(wheel3);
            consumer.accept(wheel4);
            consumer.accept(wheel5);
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
            l_pin.delete();
            r_pin.delete();
            l_slider.delete();
            r_slider.delete();
            wheel1.delete();
            wheel2.delete();
            wheel3.delete();
            wheel4.delete();
            wheel5.delete();
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

    public static class QuintupleAxleExtraLargeScotchYoke extends QuintupleAxleScotchYokeVisual {
        private final TransformedInstance frame;
        private final ScrollTransformedInstance belt;
        private final TransformedInstance r_pin;
        private final TransformedInstance l_pin;
        private final TransformedInstance r_slider;
        private final TransformedInstance l_slider;
        private final TransformedInstance wheel1;
        private final TransformedInstance wheel2;
        private final TransformedInstance wheel3;
        private final TransformedInstance wheel4;
        private final TransformedInstance wheel5;
        private final TransformedInstance shaft1;
        private final TransformedInstance shaft2;
        private final TransformedInstance shaft3;
        private final TransformedInstance shaft4;
        private final TransformedInstance shaft5;
        private final TransformedInstance shaft6;
        private final TransformedInstance shaft7;
        private final TransformedInstance shaft8;
        private final TransformedInstance shaft9;
        private final TransformedInstance shaft10;
        private final TransformedInstance shaft11;
        private final TransformedInstance shaft12;

        public QuintupleAxleExtraLargeScotchYoke(VisualizationContext ctx, float partialTick, boolean inContraption) {
            super(ctx, partialTick, inContraption);

            frame = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_10C_FRAME))
                    .createInstance();

            belt = ctx.instancerProvider()
                    .instancer(AllInstanceTypes.SCROLLING_TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_10P_BELTS))
                    .createInstance();

            r_pin = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_10C_R_PIN))
                    .createInstance();

            l_pin = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_10C_L_PIN))
                    .createInstance();

            r_slider = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_10C_R_SLIDER))
                    .createInstance();

            l_slider = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_10C_L_SLIDER))
                    .createInstance();

            var wheelInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS));

            var wheelBlindInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS_BLIND));

            var wheelSemiBlindInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(BogiePartials.EXTRA_LARGE_SHARED_WHEELS_SEMI_BLIND));

            var shaftInstancer = ctx.instancerProvider()
                    .instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SHAFT));

            wheel1 = wheelInstancer.createInstance();
            wheel2 = wheelInstancer.createInstance();
            wheel3 = wheelSemiBlindInstancer.createInstance();
            wheel4 = wheelSemiBlindInstancer.createInstance();
            wheel5 = wheelBlindInstancer.createInstance();

            belt.setSpriteShift(AllSpriteShifts.BOGEY_BELT);

            shaft1 = shaftInstancer.createInstance();
            shaft2 = shaftInstancer.createInstance();
            shaft3 = shaftInstancer.createInstance();
            shaft4 = shaftInstancer.createInstance();
            shaft5 = shaftInstancer.createInstance();
            shaft6 = shaftInstancer.createInstance();
            shaft7 = shaftInstancer.createInstance();
            shaft8 = shaftInstancer.createInstance();
            shaft9 = shaftInstancer.createInstance();
            shaft10 = shaftInstancer.createInstance();
            shaft11 = shaftInstancer.createInstance();
            shaft12 = shaftInstancer.createInstance();
        }
        @Override
        public void update(CompoundTag bogeyData, float wheelAngle, PoseStack poseStack) {
            super.update(bogeyData, wheelAngle, poseStack);
            frame.setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            r_pin.setTransform(poseStack)
                    .translate(0, 1.25f, 0)
                    .rotateXDegrees((wheelAngle))
                    .translate(0, .375f, 0)
                    .rotateXDegrees(-(wheelAngle))
                    .setChanged();

            l_pin.setTransform(poseStack)
                    .translate(0, 1.25f, 0)
                    .rotateXDegrees((wheelAngle + 90))
                    .translate(0, .375f, 0)
                    .rotateXDegrees(-(wheelAngle + 90))
                    .setChanged();

            r_slider.setTransform(poseStack)
                    .translate(0, 0, .375f * Math.sin(AngleHelper.rad(wheelAngle)))
                    .setChanged();

            l_slider.setTransform(poseStack)
                    .translate(0, 0, .375f * Math.sin(AngleHelper.rad(wheelAngle+90)))
                    .setChanged();

            belt.offset(0, BELT_RADIUS_IN_UV_SPACE * Mth.DEG_TO_RAD * wheelAngle)
                    .setTransform(poseStack)
                    .scale(1 - 1 / 512f)
                    .setChanged();

            wheel1.setTransform(poseStack)
                    .translate(0, 1.25f, -4.5f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel2.setTransform(poseStack)
                    .translate(0, 1.25f, 4.5f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel3.setTransform(poseStack)
                    .translate(0, 1.25f, -2.25f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel4.setTransform(poseStack)
                    .translate(0, 1.25f, 2.25f)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            wheel5.setTransform(poseStack)
                    .translate(0, 1.25f, 0)
                    .rotateXDegrees(wheelAngle)
                    .setChanged();

            shaft1.setTransform(poseStack)
                    .translate(-.5f, .25f, -6)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft2.setTransform(poseStack)
                    .translate(-.5f, .25f, 5)
                    .center()
                    .rotateTo(Direction.UP, Direction.EAST)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft3.setTransform(poseStack)
                    .translate(-.5f, .25f, -5.5625f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft4.setTransform(poseStack)
                    .translate(-.5f, .25f, 4.5625f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft5.setTransform(poseStack)
                    .translate(-.5f, .25f, -4.375f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft6.setTransform(poseStack)
                    .translate(-.5f, .25f, 3.375f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft7.setTransform(poseStack)
                    .translate(-.5f, .25f, -3.3125f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft8.setTransform(poseStack)
                    .translate(-.5f, .25f, 2.3125f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft9.setTransform(poseStack)
                    .translate(-.5f, .25f, -2.1875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft10.setTransform(poseStack)
                    .translate(-.5f, .25f, 1.1875f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft11.setTransform(poseStack)
                    .translate(-.5f, .25f, -1.1625f)
                    .center()
                    .rotateTo(Direction.UP, Direction.SOUTH)
                    .rotateYDegrees(wheelAngle)
                    .uncenter()
                    .setChanged();

            shaft12.setTransform(poseStack)
                    .translate(-.5f, .25f, .1625f)
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
            l_pin.setZeroTransform().setChanged();
            r_pin.setZeroTransform().setChanged();
            l_slider.setZeroTransform().setChanged();
            r_slider.setZeroTransform().setChanged();
            wheel1.setZeroTransform().setChanged();
            wheel2.setZeroTransform().setChanged();
            wheel3.setZeroTransform().setChanged();
            wheel4.setZeroTransform().setChanged();
            wheel5.setZeroTransform().setChanged();
            shaft1.setZeroTransform().setChanged();
            shaft2.setZeroTransform().setChanged();
            shaft3.setZeroTransform().setChanged();
            shaft4.setZeroTransform().setChanged();
            shaft5.setZeroTransform().setChanged();
            shaft6.setZeroTransform().setChanged();
            shaft7.setZeroTransform().setChanged();
            shaft8.setZeroTransform().setChanged();
            shaft9.setZeroTransform().setChanged();
            shaft10.setZeroTransform().setChanged();
            shaft11.setZeroTransform().setChanged();
            shaft12.setZeroTransform().setChanged();
        }

        @Override
        public void updateLight(int packedLight) {
            super.updateLight(packedLight);
            frame.light(packedLight).setChanged();
            belt.light(packedLight).setChanged();
            l_pin.light(packedLight).setChanged();
            r_pin.light(packedLight).setChanged();
            l_slider.light(packedLight).setChanged();
            r_slider.light(packedLight).setChanged();
            wheel1.light(packedLight).setChanged();
            wheel2.light(packedLight).setChanged();
            wheel3.light(packedLight).setChanged();
            wheel4.light(packedLight).setChanged();
            wheel5.light(packedLight).setChanged();
            shaft1.light(packedLight).setChanged();
            shaft2.light(packedLight).setChanged();
            shaft3.light(packedLight).setChanged();
            shaft4.light(packedLight).setChanged();
            shaft5.light(packedLight).setChanged();
            shaft6.light(packedLight).setChanged();
            shaft7.light(packedLight).setChanged();
            shaft8.light(packedLight).setChanged();
            shaft9.light(packedLight).setChanged();
            shaft10.light(packedLight).setChanged();
            shaft11.light(packedLight).setChanged();
            shaft12.light(packedLight).setChanged();
        }

        @Override
        public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
            super.collectCrumblingInstances(consumer);
            consumer.accept(frame);
            consumer.accept(belt);
            consumer.accept(l_pin);
            consumer.accept(r_pin);
            consumer.accept(l_slider);
            consumer.accept(r_slider);
            consumer.accept(wheel1);
            consumer.accept(wheel2);
            consumer.accept(wheel3);
            consumer.accept(wheel4);
            consumer.accept(wheel5);
            consumer.accept(shaft1);
            consumer.accept(shaft2);
            consumer.accept(shaft3);
            consumer.accept(shaft4);
            consumer.accept(shaft5);
            consumer.accept(shaft6);
            consumer.accept(shaft7);
            consumer.accept(shaft8);
            consumer.accept(shaft9);
            consumer.accept(shaft10);
            consumer.accept(shaft11);
            consumer.accept(shaft12);
        }

        @Override
        public void delete() {
            super.delete();
            frame.delete();
            belt.delete();
            l_pin.delete();
            r_pin.delete();
            l_slider.delete();
            r_slider.delete();
            wheel1.delete();
            wheel2.delete();
            wheel3.delete();
            wheel4.delete();
            wheel5.delete();
            shaft1.delete();
            shaft2.delete();
            shaft3.delete();
            shaft4.delete();
            shaft5.delete();
            shaft6.delete();
            shaft7.delete();
            shaft8.delete();
            shaft9.delete();
            shaft10.delete();
            shaft11.delete();
            shaft12.delete();
        }
    }
}
