package com.weido.create_bb.mixin;

import com.simibubi.create.content.contraptions.render.ContraptionVisual;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ContraptionVisual.class)
public interface ContraptionVisualAccessor {
    @Accessor("lightPaddingBlocks")
    int getLightPaddingBlocks();

    @Accessor("lightPaddingBlocks")
    void setLightPaddingBlocks(int value);
}
