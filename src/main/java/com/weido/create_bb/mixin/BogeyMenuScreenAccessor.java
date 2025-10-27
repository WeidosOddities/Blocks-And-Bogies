package com.weido.create_bb.mixin;


import com.railwayteam.railways.content.bogey_menu.BogeyMenuScreen;
import com.railwayteam.railways.registry.CRGuiTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BogeyMenuScreen.class)
public interface BogeyMenuScreenAccessor {
    @Accessor("background")
    CRGuiTextures getBackground();
}
