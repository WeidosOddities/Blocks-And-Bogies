package com.weido.create_bb.mixin;

import com.simibubi.create.content.trains.bogey.AbstractBogeyBlock;
import com.weido.create_bb.data.BogieFunctionality;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBogeyBlock.class)
public abstract class AbstractBogeyBlockMixin {
    @Inject(method = "onInteractWithBogey", at = @At("HEAD"), cancellable = true, remap = false)
    private void create_bb$onInteractWithBogey(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<ItemInteractionResult> cir) {
        cir.setReturnValue(BogieFunctionality.BogeyMenuInteraction(level, pos, player, hand));
    }
}
