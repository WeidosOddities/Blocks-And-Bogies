package com.weido.create_bb.mixin;

import net.neoforged.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Mixin plugin that conditionally loads Steam 'n' Rails compatibility mixins
 * only when the Railways mod is present.
 */
public class SNRMixinPlugin implements IMixinConfigPlugin {
    private static Boolean railwaysLoaded = null;

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return isRailwaysLoaded();
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean isRailwaysLoaded() {
        if (railwaysLoaded == null) {
            // Use LoadingModList to check at mixin application time without loading classes
            railwaysLoaded = LoadingModList.get().getModFileById("railways") != null;
        }
        return railwaysLoaded;
    }
}
