package com.weido.create_bb.mixin;

import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.simibubi.create.content.trains.bogey.BogeySizes.BogeySize;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.content.trains.station.StationBlockEntity;
import com.simibubi.create.content.trains.track.ITrackBlock;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import net.createmod.catnip.data.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import com.weido.create_bb.data.menu.Entry.StyleMenuHandler;

@Mixin(value = StationBlockEntity.class, remap = false)
public abstract class StationBlockEntityMixin extends SmartBlockEntity {

    private StationBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "trackClicked", at = @At("HEAD"))
            private void storePlayer(Player player, InteractionHand hand, ITrackBlock track, BlockState state, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        StyleMenuHandler.setCurrentPlayer(player.getUUID());
    }

    @Inject(method = "trackClicked", at = @At("RETURN"))
            private void clearPlayer(Player player, InteractionHand hand, ITrackBlock track, BlockState state, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        StyleMenuHandler.setCurrentPlayer(null);
    }
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @Inject(
            method = "trackClicked",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            remap = true,
            require = 0)
    private void create_bb$setBogeyData(Player player, InteractionHand hand, ITrackBlock track, BlockState state, BlockPos pos, CallbackInfoReturnable<Boolean> cir, BoundingBox bb, BlockPos up, BlockPos down, int bogeyOffset, ItemStack handItem, boolean upsideDown, BlockPos targetPos) {
        Pair<BogeyStyle, BogeySize> styleData = StyleMenuHandler.getStyle(player.getUUID());
        BogeyStyle style = styleData.getFirst();

        if (level != null && level.getBlockEntity(targetPos) instanceof AbstractBogeyBlockEntity bogeyBE) {
            bogeyBE.setBogeyStyle(style);
        }
    }
}
