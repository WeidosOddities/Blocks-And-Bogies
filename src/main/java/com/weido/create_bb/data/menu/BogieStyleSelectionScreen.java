package com.weido.create_bb.data.menu;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.Set;
import java.util.stream.Collectors;
import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlock;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.widget.IconButton;
import com.simibubi.create.foundation.gui.widget.ScrollInput;
import com.weido.create_bb.data.menu.Entry.StyleEntryManager;
import com.weido.create_bb.data.packets.BogieStylePacket;
import net.createmod.catnip.platform.CatnipServices;
import net.createmod.catnip.gui.AbstractSimiScreen;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import com.weido.create_bb.registry.BlocksBogiesGuiTextures;
import com.weido.create_bb.data.menu.Entry.StyleEntry;
import com.weido.create_bb.data.menu.Input.*;

import org.lwjgl.glfw.GLFW;

public class BogieStyleSelectionScreen extends AbstractSimiScreen {
    private final BlocksBogiesGuiTextures background = BlocksBogiesGuiTextures.BOGIE_MENU;
    private final BlockPos targetPos;
    private static final float MIN_SCALE = 10.0f;
    private static final float MAX_SCALE = 96.0f;
    private static boolean firstTime = true;
    private boolean isDragging = false;
    private boolean isMoving = false;
    private int previewX;
    private int previewY;
    private float previewOffsetX = 0;
    private float previewOffsetY = 0;
    private float previewScale = 26.0f;
    private float rotationX = -20;
    private float rotationY = 45;
    private float totalRotationY = 45;
    private float wheelAngle = 0.0f;
    private double lastMouseX;
    private double lastMouseY;
    private TypeButton typeButton;
    private ScrollInput variantScroll;
    private ScrollInput valvegearScroll;
    private ScrollInput axleCountScroll;
    private ScrollInput speedScroll;
    private ScrollInput sizeScroll;
    private ScrollInput lengthScroll;
    private StyleEntry.Type currentType = StyleEntry.Type.TRUCK;

    int previewPosRight = 299;
    int previewPosBottom = 211;
    int previewWidth = 296;
    int previewHeight = 129;

    StyleEntry selectedBogey;

    public BogieStyleSelectionScreen(BlockPos pos) { this.targetPos = pos; }

    @Override
    protected void init() {
        setWindowSize(background.getWidth(), background.getHeight());
        super.init();

        previewX = guiLeft + background.getWidth() - previewPosRight;
        previewY = guiTop + background.getHeight() - previewPosBottom;

        currentType = firstTime ? StyleEntry.Type.TRUCK : BogeyStyleMenuState.getLastType();

        int buttonHeight = 18;
        int buttonLeftPos = guiLeft + 7;
        int buttonRightPos = guiLeft + background.getWidth() - 149;
        int buttonTopPos = guiTop + background.getHeight() - 75;
        int buttonBottomPos = guiTop + background.getHeight() - 53;

        IconButton confirmButton = new IconButton(guiLeft + background.getWidth() - 25, guiTop + background.getHeight() - 24, AllIcons.I_CONFIRM)
                .withCallback(this::onClose);

        typeButton = new TypeButton(buttonRightPos, buttonBottomPos)
            .withCallback(() -> {
                currentType = (currentType == StyleEntry.Type.TRUCK ? StyleEntry.Type.DRIVER : StyleEntry.Type.TRUCK);
                updateValidOptions();
                updateSelectedBogey();
                updateSizeSelection();
            });

        variantScroll = new VariantScrollInput(buttonLeftPos, buttonTopPos, 142, buttonHeight)
            .setState(firstTime ? 0 : BogeyStyleMenuState.getLastVariant());

        valvegearScroll = new ValveGearScrollInput(buttonRightPos, buttonTopPos, 142, buttonHeight)
            .setState(firstTime ? 0 : BogeyStyleMenuState.getLastValvegear());

        axleCountScroll = new AxleCountScrollInput(buttonLeftPos, buttonBottomPos, 78, buttonHeight)
            .setState(firstTime ? 2 : BogeyStyleMenuState.getLastAxleCount());

        sizeScroll = new SizeScrollInput(guiLeft + background.getWidth() - 58, buttonBottomPos, 51, buttonHeight);

        lengthScroll = new LengthScrollInput(guiLeft + background.getWidth() - 213, buttonBottomPos, 60, buttonHeight)
            .setState(firstTime ? 0 : BogeyStyleMenuState.getLastBogieLength());

        speedScroll = new SpeedScrollInput(buttonLeftPos - 1, guiTop + 127, 31, 15)
            .setState(5);

        addRenderableWidget(confirmButton);
        addRenderableWidget(typeButton);
        addRenderableWidget(variantScroll);
        addRenderableWidget(valvegearScroll);
        addRenderableWidget(axleCountScroll);
        addRenderableWidget(speedScroll);
        addRenderableWidget(sizeScroll);
        addRenderableWidget(lengthScroll);

        updateValidOptions();
        updateSelectedBogey();
        setupScrollCallbacks();
        updateSizeSelection();

        sizeScroll.setState(firstTime ? 1 : BogeyStyleMenuState.getLastSize());
    }

    @Override
    public void renderBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.renderBackground(graphics, mouseX, mouseY, partialTicks);
        background.render(graphics, guiLeft, guiTop);
    }

    @Override
    protected void renderWindow(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        if (selectedBogey != null && minecraft != null && minecraft.level != null) {
            int previewX = guiLeft + background.getWidth() - previewPosRight;
            int previewY = guiTop + background.getHeight() - previewPosBottom;

            graphics.enableScissor(previewX, previewY, previewX + previewWidth, previewY + previewHeight);
            renderModelPreview(graphics, partialTicks);
            graphics.disableScissor();
        }
        BlocksBogiesGuiTextures.NUMBER_BACKGROUND.render(graphics, speedScroll.getX(), speedScroll.getY());
    }

    @Override
    public void renderWindowForeground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.renderWindowForeground(graphics, mouseX, mouseY, partialTicks);
        Component title = Component.translatable("create_bb.menu.title");

        int titleX = guiLeft + background.getWidth()/2 - font.width(title)/2;
        int titleY = guiTop + 4;

        graphics.drawString(font, title, titleX, titleY, 0x592424, false);

        renderAllText(graphics);
    }

    @Override
    public void tick() { super.tick(); }

    @Override
    public void onClose() {
        firstTime = false;
        BogeyStyleMenuState.saveState(
            currentType,
            variantScroll.getState(),
            valvegearScroll.getState(),
            axleCountScroll.getState(),
            sizeScroll.getState(),
            lengthScroll.getState()
        );
        BogieStylePacket.Serverbound packet = new BogieStylePacket.Serverbound(
            selectedBogey.bogeyStyle(),
            ((SizeScrollInput) sizeScroll).getCurrentSize(),
            targetPos
        );
        CatnipServices.NETWORK.sendToServer(packet);
        super.onClose();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int speedX = speedScroll.getX();
        int speedY = speedScroll.getY();
        int numberWidth = BlocksBogiesGuiTextures.NUMBER_BACKGROUND.getWidth();
        int numberHeight = BlocksBogiesGuiTextures.NUMBER_BACKGROUND.getHeight();

        if (mouseX >= speedX && mouseX <= speedX + numberWidth &&
            mouseY >= speedY && mouseY <= speedY + numberHeight) {
            return speedScroll.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
        }

        int previewX = guiLeft + background.getWidth() - previewPosRight;
        int previewY = guiTop + background.getHeight() - previewPosBottom;

        if (mouseX >= previewX && mouseX <= previewX + previewWidth &&
            mouseY >= previewY && mouseY <= previewY + previewHeight) {
            previewScale = (float) Mth.clamp(previewScale + verticalAmount * 2.0f, MIN_SCALE, MAX_SCALE);
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (mouseX >= previewX && mouseX <= previewX + previewWidth &&
                mouseY >= previewY && mouseY <= previewY + previewHeight) {
            if (button == 0) {
                isDragging = true;
                lastMouseX = mouseX;
                lastMouseY = mouseY;
            } else if (button == 1) {
                isMoving = true;
                lastMouseX = mouseX;
                lastMouseY = mouseY;
            }
            if (minecraft != null) {
                GLFW.glfwSetInputMode(minecraft.getWindow().getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
            }
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (isDragging || isMoving) {
            if (minecraft != null) {
                double scaleFactor = minecraft.getWindow().getGuiScale();
                double centerX = (previewX + previewWidth / 2.0) * scaleFactor;
                double centerY = (previewY + previewHeight / 2.0) * scaleFactor;

                if (isDragging) {
                    float deltaX = (float) ((mouseX - lastMouseX) * 0.5f);
                    float deltaY = (float) ((mouseY - lastMouseY) * 0.5f);

                    if (hasShiftDown()) {
                        totalRotationY += deltaX;
                        rotationY = Math.round(totalRotationY / 90f) * 90f;
                        rotationX = 0;
                    } else {
                        totalRotationY += deltaX;
                        rotationY = totalRotationY % 360;
                        rotationX = Mth.clamp(rotationX - deltaY, -22.5f, 22.5f);
                    }
                } else {
                    previewOffsetX += (float) (mouseX - lastMouseX);
                    previewOffsetY += (float) (mouseY - lastMouseY);
                }

                GLFW.glfwSetCursorPos(minecraft.getWindow().getWindow(), centerX, centerY);
                lastMouseX = centerX / scaleFactor;
                lastMouseY = centerY / scaleFactor;
            }
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (isDragging || isMoving) {
            isDragging = false;
            isMoving = false;
            if (minecraft != null) {
                GLFW.glfwSetInputMode(minecraft.getWindow().getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
            }
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }
    //This is probably really inefficient, I might change it later
    private void updateValidOptions() {
        Set<StyleEntry.ValveGear> validValveGears;
        if (currentType == StyleEntry.Type.TRUCK) {
            validValveGears = Set.of(StyleEntry.ValveGear.NONE);
            valvegearScroll.setState(0);
        } else {
            validValveGears = StyleEntryManager.getBogeyEntryList().stream()
                .filter(entry -> entry.type() == StyleEntry.Type.DRIVER)
                .map(StyleEntry::valveGear)
                .collect(Collectors.toSet());
        }

        ((ValveGearScrollInput) valvegearScroll).updateOptions(validValveGears);

        final StyleEntry.ValveGear currentValveGear = ((ValveGearScrollInput) valvegearScroll).valveGears[valvegearScroll.getState()];

        Set<StyleEntry.Variant> validVariants = StyleEntryManager.getBogeyEntryList().stream()
            .filter(entry -> entry.type() == currentType)
            .filter(entry -> entry.valveGear() == currentValveGear)
            .map(StyleEntry::variant)
            .collect(Collectors.toSet());

        ((VariantScrollInput) variantScroll).updateOptions(validVariants);

        StyleEntry.Variant variant = ((VariantScrollInput) variantScroll).variants[variantScroll.getState()];
        if (!validVariants.contains(variant)) {
            variantScroll.setState(0);
            variant = ((VariantScrollInput) variantScroll).variants[0];
        }
        final StyleEntry.Variant currentVariant = variant;

        Set<Integer> validAxleCounts = StyleEntryManager.getBogeyEntryList().stream()
            .filter(entry -> entry.type() == currentType)
            .filter(entry -> entry.variant() == currentVariant)
            .filter(entry -> entry.valveGear() == currentValveGear)
            .map(StyleEntry::axleCount)
            .collect(Collectors.toSet());

        int minAxles = validAxleCounts.stream().min(Integer::compareTo).orElse(2);
        int maxAxles = validAxleCounts.stream().max(Integer::compareTo).orElse(2);

        ((AxleCountScrollInput) axleCountScroll).updateOptions(minAxles, maxAxles);

        int currentAxleCount = axleCountScroll.getState();

        Set<BogeySizes.BogeySize> validSizes = StyleEntryManager.getBogeyEntryList().stream()
            .filter(entry -> entry.type() == currentType)
            .filter(entry -> entry.variant() == currentVariant)
            .filter(entry -> entry.valveGear() == currentValveGear)
            .filter(entry -> entry.axleCount() == currentAxleCount)
            .flatMap(entry -> entry.bogeyStyle().validSizes().stream())
            .collect(Collectors.toSet());

        BogeySizes.BogeySize currentSize = ((SizeScrollInput) sizeScroll).getCurrentSize();

        ((SizeScrollInput) sizeScroll).updateOptions(validSizes);

        if (!validSizes.contains(currentSize)) {
            BogeySizes.BogeySize fallbackSize = validSizes.stream()
                .findFirst()
                .orElse(BogeySizes.SMALL);

            sizeScroll.setState(validSizes.stream()
                .toList()
                .indexOf(fallbackSize));
        } else {
            sizeScroll.setState(validSizes.stream()
                .toList()
                .indexOf(currentSize));
        }

        Set<StyleEntry.Length> validLengths = StyleEntryManager.getBogeyEntryList().stream()
            .filter(entry -> entry.type() == currentType)
            .filter(entry -> entry.variant() == currentVariant)
            .filter(entry -> entry.valveGear() == currentValveGear)
            .filter(entry -> entry.axleCount() == currentAxleCount)
            .map(StyleEntry::length)
            .collect(Collectors.toSet());

        ((LengthScrollInput) lengthScroll).updateOptions(validLengths);

        StyleEntry.Length currentLength = ((LengthScrollInput) lengthScroll).bogieLength[lengthScroll.getState()];
        if (!validLengths.contains(currentLength)) {
            lengthScroll.setState(0);
        }
        updateSelectedBogey();
    }

    private void updateSelectedBogey() {
        VariantScrollInput variantInput = (VariantScrollInput) variantScroll;
        ValveGearScrollInput valveGearInput = (ValveGearScrollInput) valvegearScroll;
        LengthScrollInput lengthInput = (LengthScrollInput) lengthScroll;

        StyleEntry.Variant variant = variantInput.variants[variantScroll.getState()];
        StyleEntry.ValveGear valveGear = valveGearInput.valveGears[valvegearScroll.getState()];
        StyleEntry.Length length = lengthInput.bogieLength[lengthScroll.getState()];

        int axleCount = axleCountScroll.getState();

        selectedBogey = StyleEntryManager.getBogeyEntryList().stream()
            .filter(entry -> entry.variant() == variant &&
                entry.valveGear() == valveGear &&
                entry.type() == currentType &&
                entry.axleCount() == axleCount &&
                entry.length() == length)
            .findFirst()
            .orElseGet(() -> StyleEntry.getOrCreate(
                AllBogeyStyles.STANDARD,
                variant,
                valveGear,
                currentType,
                axleCount,
                length
            ));
    }

    private void renderButtonText(GuiGraphics graphics, TypeButton button, Component text) {
        int textWidth = font.width(text);
        int buttonX = button.getX();
        int buttonY = button.getY();
        graphics.drawString(font, text,
            buttonX + (button.getWidth() - textWidth) / 2,
            buttonY + 5,
            0xFFFFFF, true);
    }

    private void renderScrollText(GuiGraphics graphics, ScrollInput scroll, Component text) {
        int textWidth = font.width(text);
        int scrollX = scroll.getX();
        int scrollY = scroll.getY();

        graphics.drawString(font, text,
            scrollX + (scroll.getWidth() - textWidth) / 2 + 1,
            scrollY + 5,
            0xFFFFFF, true);
    }

    private void renderScrollNumber(GuiGraphics graphics, ScrollInput scroll, Component text) {
        int textWidth = font.width(text);
        int scrollX = scroll.getX();
        int scrollY = scroll.getY();
        graphics.drawString(font, text,
            scrollX + 1 + (scroll.getWidth() - textWidth) / 2,
            scrollY + 4,
            0x592424, false);
    }

    private void renderModelPreview(GuiGraphics graphics, float partialTicks) {
        int speedX = speedScroll.getX();
        int speedY = speedScroll.getY();
        int numberWidth = BlocksBogiesGuiTextures.NUMBER_BACKGROUND.getWidth();
        int numberHeight = BlocksBogiesGuiTextures.NUMBER_BACKGROUND.getHeight();

        graphics.enableScissor(previewX, previewY, speedX + 1, previewY + previewHeight);
        renderModel(graphics, partialTicks);
        graphics.disableScissor();

        graphics.enableScissor(speedX + numberWidth - 1, previewY, previewX + previewWidth, previewY + previewHeight);
        renderModel(graphics, partialTicks);
        graphics.disableScissor();

        graphics.enableScissor(speedX, previewY, speedX + numberWidth, speedY + 1);
        renderModel(graphics, partialTicks);
        graphics.disableScissor();

        graphics.enableScissor(speedX, speedY + numberHeight - 1, speedX + numberWidth, previewY + previewHeight);
        renderModel(graphics, partialTicks);
        graphics.disableScissor();
    }

    private void renderModel(GuiGraphics graphics, float partialTicks) {
        if (selectedBogey == null || minecraft == null || minecraft.level == null) return;

        BogeyStyle style = selectedBogey.bogeyStyle();
        BogeySizes.BogeySize renderSize = ((SizeScrollInput) sizeScroll).getCurrentSize();

        PoseStack poseStack = graphics.pose();
        poseStack.pushPose();

        int backgroundWidth = background.getWidth();
        poseStack.translate(guiLeft + backgroundWidth/2f + previewOffsetX, guiTop + background.getHeight() - 151 + previewOffsetY, -100);
        poseStack.scale(1, 1, -1);
        poseStack.translate(0, 0, -100);
        poseStack.scale(previewScale, previewScale, previewScale);

        poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        poseStack.mulPose(Axis.XP.rotationDegrees(rotationX));
        poseStack.mulPose(Axis.YP.rotationDegrees(rotationY));

        Lighting.setupForEntityInInventory();

        MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
        int light = 0xF000F0;
        int overlay = OverlayTexture.NO_OVERLAY;

        wheelAngle = (wheelAngle + (-speedScroll.getState() / 10f) * partialTicks) % 360;

        BlockState bogeyState = style.getBlockForSize(renderSize)
            .defaultBlockState()
            .setValue(AbstractBogeyBlock.AXIS, Direction.Axis.Z);

        CachedBuffers.block(bogeyState)
            .translate(-0.5f, -0.5f, -0.5f)
            .light(light)
            .overlay(overlay)
            .renderInto(poseStack, bufferSource.getBuffer(RenderType.cutoutMipped()));

        poseStack.translate(0, 0, 0);

        style.render(renderSize, partialTicks, poseStack, bufferSource, light, overlay, wheelAngle, null, false);

        bufferSource.endBatch();
        poseStack.popPose();
    }

    private void renderAllText(GuiGraphics graphics) {
        VariantScrollInput variantInput = (VariantScrollInput) variantScroll;
        ValveGearScrollInput valveGearInput = (ValveGearScrollInput) valvegearScroll;
        LengthScrollInput lengthInput = (LengthScrollInput) lengthScroll;

        Component typeText = Component.translatable("create_bb.menu.type").append(currentType.getDisplayText());
        renderButtonText(graphics, typeButton, typeText);

        Component variantText = Component.translatable("create_bb.menu.variant").append(variantInput.variants[variantScroll.getState()].getDisplayText());
        renderScrollText(graphics, variantScroll, variantText);
        disabledScroll(graphics, variantScroll, variantInput.variants.length);

        Component valveGearText = Component.translatable("create_bb.menu.valve_gear").append(valveGearInput.valveGears[valvegearScroll.getState()].getDisplayText());
        renderScrollText(graphics, valvegearScroll, valveGearText);
        disabledScroll(graphics, valvegearScroll, valveGearInput.valveGears.length);

        Component axleText = Component.translatable("create_bb.menu.axle_count").append(String.valueOf(axleCountScroll.getState()));
        renderScrollText(graphics, axleCountScroll, axleText);
        disabledScroll(graphics, axleCountScroll, ((AxleCountScrollInput) axleCountScroll).getMaxAxles() == ((AxleCountScrollInput) axleCountScroll).getMinAxles() ? 1 : 0);

        Component speedText = Component.literal(String.valueOf(speedScroll.getState()));
        renderScrollNumber(graphics, speedScroll, speedText);

        String sizeKey = ((SizeScrollInput) sizeScroll).getCurrentSize() == BogeySizes.LARGE ?
                (currentType == StyleEntry.Type.DRIVER ? "create_bb.menu.size.extra_large" : "create_bb.menu.size.large") : (currentType == StyleEntry.Type.DRIVER ? "create_bb.menu.size.large" : "create_bb.menu.size.small");

        Component sizeText = Component.translatable(sizeKey);
        renderScrollText(graphics, sizeScroll, sizeText);
        disabledScroll(graphics, sizeScroll, ((SizeScrollInput) sizeScroll).getSizes().length);

        Component lengthText = lengthInput.bogieLength[lengthScroll.getState()].getDisplayText();
        renderScrollText(graphics, lengthScroll, lengthText);
        disabledScroll(graphics, lengthScroll, lengthInput.bogieLength.length);

    }

    private void disabledScroll(GuiGraphics graphics, ScrollInput scrollInput, int optionsLength) {
        if (optionsLength == 1) {
            graphics.fill(
                scrollInput.getX(), scrollInput.getY(),
                scrollInput.getX() + scrollInput.getWidth(), scrollInput.getY() + scrollInput.getHeight(),
                0x80333333
            );
        }
    }

    private void setupScrollCallbacks() {
        ScrollInput[] scrolls = {variantScroll, valvegearScroll, axleCountScroll, sizeScroll, lengthScroll};
        for (ScrollInput scroll : scrolls) {
            scroll.calling(state -> {
                updateSelectedBogey();
                updateValidOptions();
            });
        }
    }

    private void updateSizeSelection() {
        ((SizeScrollInput) sizeScroll).setDriver(currentType == StyleEntry.Type.DRIVER);
    }
}