package com.weido.create_bb.mixin;

import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.CarriageContraptionVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CarriageContraptionVisual.class)
public class CarriageContraptionVisualMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstruct(VisualizationContext context, CarriageContraptionEntity entity, float partialTick, CallbackInfo ci) {
        ContraptionVisualAccessor accessor = (ContraptionVisualAccessor) this;
        int current = accessor.getLightPaddingBlocks();
        accessor.setLightPaddingBlocks(current + 3);
    }
}