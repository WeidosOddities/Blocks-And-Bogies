package com.weido.create_bb.mixin;

import com.railwayteam.railways.content.bogey_menu.BogeyMenuScreen;
import com.railwayteam.railways.content.bogey_menu.handler.BogeyMenuHandlerClient;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.widget.IconButton;
import com.weido.create_bb.data.compat.steamnrails.JankWidgetRenderer;
import com.weido.create_bb.data.compat.steamnrails.MenuSwitchButton;
import com.weido.create_bb.data.menu.BogieStyleSelectionScreen;
import com.weido.create_bb.data.packets.BogieStylePacket;
import net.createmod.catnip.gui.ScreenOpener;
import net.createmod.catnip.platform.CatnipServices;
import net.createmod.catnip.gui.widget.AbstractSimiWidget;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BogeyMenuScreen.class)
public abstract class BogeyMenuScreenMixin {
    @Inject(method = "init", at = @At("TAIL"))
    private void create_bb$injectSwitchButton(CallbackInfo ci) {
        BogeyMenuScreen self = (BogeyMenuScreen) (Object) this;
        AbstractSimiScreenAccessor abstractSimiScreenAccessor = (AbstractSimiScreenAccessor) self;

        int x = abstractSimiScreenAccessor.getGuiLeft();
        int y = abstractSimiScreenAccessor.getGuiTop();
        var background = ((BogeyMenuScreenAccessor) this).getBackground();
        var targetPos = MenuSwitchButton.getTargetPos();

        if (background != null) {
            IconButton switchButton = new IconButton(
                    x + background.width - 62,
                    y + background.height - 24,
                    AllIcons.I_DICE
            );
            switchButton.withCallback(() -> ScreenOpener.open(new BogieStyleSelectionScreen(targetPos)));
            switchButton.setToolTip(Component.translatable("create_bb.tooltips.switch_to_bogey_menu").withStyle(s -> s.withColor(AbstractSimiWidget.HEADER_RGB.getRGB())));

            JankWidgetRenderer.addRenderableWidgetReflect(self, switchButton);
        }
    }

    @Inject(method = "onMenuClose", at = @At("TAIL"), remap = false)
    private void create_bb$onMenuClose(CallbackInfo ci) {
        BogeyMenuScreen self = (BogeyMenuScreen)(Object) this;
        try {
            var selectedBogeyField = BogeyMenuScreen.class.getDeclaredField("selectedBogey");
            selectedBogeyField.setAccessible(true);
            var selectedBogey = selectedBogeyField.get(self);

            var style = selectedBogey.getClass().getMethod("bogeyStyle").invoke(selectedBogey);
            BogeySizes.BogeySize size = BogeyMenuHandlerClient.getSize((BogeyStyle) style);
            var targetPos = MenuSwitchButton.getTargetPos();

            BogieStylePacket.Serverbound packet = new BogieStylePacket.Serverbound((BogeyStyle) style, size, targetPos);
            CatnipServices.NETWORK.sendToServer(packet);

            MenuSwitchButton.setTargetPos(null);

        } catch (Exception ignored) { }
    }
}
