package com.weido.create_bb.mixin;

import com.weido.create_bb.data.compat.steamnrails.MenuSwitchButton;
import com.weido.create_bb.data.menu.BogieStyleSelectionScreen;
import com.weido.create_bb.registry.BlocksBogiesGuiTextures;
import net.createmod.catnip.gui.AbstractSimiScreen;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BogieStyleSelectionScreen.class, remap = false)
public abstract class BogieStyleSelectionScreenMixin extends AbstractSimiScreen {
    @Shadow @Final private BlocksBogiesGuiTextures background;
    @Shadow @Final private BlockPos targetPos;

    @Inject(method = "init", at = @At("TAIL"), remap = true)
    private void create_bb$injectSwitchButton(CallbackInfo ci) {
        var switchButton = MenuSwitchButton.create(
                guiLeft + background.getWidth() - 54,
                guiTop + background.getHeight() - 24,
                targetPos,
                () -> {}
        );
        addRenderableWidget(switchButton);
    }
}
