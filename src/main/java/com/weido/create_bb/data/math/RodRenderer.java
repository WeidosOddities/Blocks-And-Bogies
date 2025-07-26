package com.weido.create_bb.data.math;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import net.createmod.catnip.render.SuperByteBuffer;

public class RodRenderer {
    public record RenderingResults(
            int side,
            int light,
            int overlay,
            float wheelAngle,
            boolean forwards,
            PoseStack poseStack,
            VertexConsumer vertexConsumer,
            RodCalculations.WalschaertsParameters params
    ) {}

    public record VisualResults(
            int side,
            float wheelAngle,
            boolean forwards,
            PoseStack poseStack,
            RodCalculations.WalschaertsParameters params
    ) {}

    private static float[] getRenderResults(RenderingResults renderingResults) {
        float wheelAngleCheck = (renderingResults.forwards() ? renderingResults.wheelAngle() : -renderingResults.wheelAngle()) + (renderingResults.side() * 90);
        return RodCalculations.calculateGear(wheelAngleCheck, renderingResults.params());
    }

    private static float[] getVisualResults(VisualResults visualResults) {
        float wheelAngleCheck = (visualResults.forwards() ? visualResults.wheelAngle() : -visualResults.wheelAngle()) + (visualResults.side() * 90);
        return RodCalculations.calculateGear(wheelAngleCheck, visualResults.params());
    }

    private static void setupBasicBuffer(SuperByteBuffer buffer, RenderingResults renderingResults) {
        buffer.rotateYDegrees(renderingResults.forwards() ? 0 : 180)
                .light(renderingResults.light())
                .overlay(renderingResults.overlay());
    }

    private static void setupBasicTransform(TransformedInstance instance, VisualResults visualResults) {
        instance.setTransform(visualResults.poseStack())
                .rotateYDegrees(visualResults.forwards() ? 0 : 180);
    }

    public static void renderMainRod(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float[] results = getRenderResults(renderingResults);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translateZ(results[0] + renderingResults.params().xOffset())
                .translateY(renderingResults.params().yOffset())
                .rotateX(results[7])
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void renderPistonRod(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float[] results = getRenderResults(renderingResults);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translateZ(results[0] + renderingResults.params().xOffset())
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void renderConnectingRod(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float angle = (renderingResults.forwards() ? renderingResults.wheelAngle() : -renderingResults.wheelAngle()) + (renderingResults.side() * 90);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translate(0, renderingResults.params().yOffset(), 0)
                .rotateXDegrees(angle)
                .translate(0, renderingResults.params().mainCrankRadius(), 0)
                .rotateXDegrees(-angle)
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void renderExpansionLink(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float[] results = getRenderResults(renderingResults);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translate(0, renderingResults.params().expansionLinkY() + renderingResults.params().yOffset(), -renderingResults.params().expansionLinkX() + renderingResults.params().xOffset())
                .rotateX(results[9])
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void renderEccentricRod(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float[] results = getRenderResults(renderingResults);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translate(0, results[2] + renderingResults.params().yOffset(), -results[1] + renderingResults.params().xOffset())
                .rotateX(results[8])
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void renderRadiusRod(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float[] results = getRenderResults(renderingResults);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translate(0, results[4] + renderingResults.params().yOffset(), -results[3] + renderingResults.params().xOffset())
                .rotateX(results[11])
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void renderUnionLink(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float[] results = getRenderResults(renderingResults);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translate(0, renderingResults.params().yOffset() - renderingResults.params().dropLink(), -results[5] + renderingResults.params().xOffset())
                .rotateX(results[10])
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void renderCombinationLever(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float[] results = getRenderResults(renderingResults);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translate(0, renderingResults.params().valveY() + renderingResults.params().yOffset(), -results[6] + renderingResults.params().xOffset())
                .rotateX(results[12])
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void renderValveStem(SuperByteBuffer buffer, RenderingResults renderingResults) {
        float[] results = getRenderResults(renderingResults);
        setupBasicBuffer(buffer, renderingResults);
        buffer.translate(0, 0, -results[6] + renderingResults.params().xOffset())
                .renderInto(renderingResults.poseStack(), renderingResults.vertexConsumer());
    }

    public static void visualizeMainRod(TransformedInstance instance, VisualResults visualResults) {
        float[] results = getVisualResults(visualResults);
        setupBasicTransform(instance, visualResults);
        instance.translateZ(results[0] + visualResults.params().xOffset())
                .translateY(visualResults.params().yOffset())
                .rotateX(results[7])
                .setChanged();
    }

    public static void visualizePistonRod(TransformedInstance instance, VisualResults visualResults) {
        float[] results = getVisualResults(visualResults);
        setupBasicTransform(instance, visualResults);
        instance.translateZ(results[0] + visualResults.params().xOffset())
                .setChanged();
    }

    public static void visualizeConnectingRod(TransformedInstance instance, VisualResults visualResults) {
        float angle = (visualResults.forwards() ? visualResults.wheelAngle() : -visualResults.wheelAngle()) + (visualResults.side() * 90);
        setupBasicTransform(instance, visualResults);
        instance.translate(0, visualResults.params().yOffset(), 0)
                .rotateXDegrees(angle)
                .translate(0, visualResults.params().mainCrankRadius(), 0)
                .rotateXDegrees(-angle)
                .setChanged();
    }

    public static void visualizeExpansionLink(TransformedInstance instance, VisualResults visualResults) {
        float[] results = getVisualResults(visualResults);
        setupBasicTransform(instance, visualResults);
        instance.translate(0, visualResults.params().expansionLinkY() + visualResults.params().yOffset(), -visualResults.params().expansionLinkX() + visualResults.params().xOffset())
                .rotateX(results[9])
                .setChanged();
    }

    public static void visualizeEccentricRod(TransformedInstance instance, VisualResults visualResults) {
        float[] results = getVisualResults(visualResults);
        setupBasicTransform(instance, visualResults);
        instance.translate(0, results[2] + visualResults.params().yOffset(), -results[1] + visualResults.params().xOffset())
                .rotateX(results[8])
                .setChanged();
    }

    public static void visualizeRadiusRod(TransformedInstance instance, VisualResults visualResults) {
        float[] results = getVisualResults(visualResults);
        setupBasicTransform(instance, visualResults);
        instance.setTransform(visualResults.poseStack())
                .rotateYDegrees(visualResults.forwards() ? 0 : 180)
                .translate(0, results[4] + visualResults.params().yOffset(), -results[3] + visualResults.params().xOffset())
                .rotateX(results[11])
                .setChanged();
    }

    public static void visualizeUnionLink(TransformedInstance instance, VisualResults visualResults) {
        float[] results = getVisualResults(visualResults);
        setupBasicTransform(instance, visualResults);
        instance.translate(0, visualResults.params().yOffset() - visualResults.params().dropLink(), -results[5] + visualResults.params().xOffset())
                .rotateX(results[10])
                .setChanged();
    }

    public static void visualizeCombinationLever(TransformedInstance instance, VisualResults visualResults) {
        float[] results = getVisualResults(visualResults);
        setupBasicTransform(instance, visualResults);
        instance.translate(0, visualResults.params().valveY() + visualResults.params().yOffset(), -results[6] + visualResults.params().xOffset())
                .rotateX(results[12])
                .setChanged();
    }

    public static void visualizeValveStem(TransformedInstance instance, VisualResults visualResults) {
        float[] results = getVisualResults(visualResults);
        setupBasicTransform(instance, visualResults);
        instance.translate(0, 0, -results[6] + visualResults.params().xOffset())
                .setChanged();
    }
}
