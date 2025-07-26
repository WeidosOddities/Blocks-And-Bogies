package com.weido.create_bb.data.menu.Input;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import com.simibubi.create.AllKeys;
import net.createmod.catnip.gui.widget.AbstractSimiWidget;
import org.jetbrains.annotations.NotNull;
import com.weido.create_bb.registry.BlocksBogiesGuiTextures;

public class TypeButton extends AbstractSimiWidget {

    public TypeButton(int x, int y) {
        super(x, y, BlocksBogiesGuiTextures.WIDE_BUTTON.getWidth(), BlocksBogiesGuiTextures.WIDE_BUTTON.getHeight());
    }

    @Override
    public void doRender(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        if (visible) {
            isHovered = mouseX >= getX() && mouseY >= getY() && mouseX < getX() + width && mouseY < getY() + height;

            BlocksBogiesGuiTextures button = !active ? BlocksBogiesGuiTextures.WIDE_BUTTON_DISABLED
                : isHovered && AllKeys.isMouseButtonDown(0) ? BlocksBogiesGuiTextures.WIDE_BUTTON_DOWN
                : isHovered ? BlocksBogiesGuiTextures.WIDE_BUTTON_HOVER
                : BlocksBogiesGuiTextures.WIDE_BUTTON;

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            graphics.blit(button.location, getX(), getY(), button.getStartX(), button.getStartY(), button.getWidth(),
                button.getHeight(), button.getTextureWidth(), button.getTextureHeight());
        }
    }
}
