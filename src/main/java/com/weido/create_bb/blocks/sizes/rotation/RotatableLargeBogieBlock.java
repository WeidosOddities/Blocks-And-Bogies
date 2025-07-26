package com.weido.create_bb.blocks.sizes.rotation;

import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.weido.create_bb.blocks.sizes.LargeBogieBlock;
import com.weido.create_bb.data.BogieFunctionality;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RotatableLargeBogieBlock extends LargeBogieBlock {
    protected RotatableLargeBogieBlock(BlockBehaviour.Properties props, BogeyStyle defaultStyle, BogeySizes.BogeySize size) {
        super(props, defaultStyle, size);
    }

    @Override
    protected ItemInteractionResult onInteractWithBogey(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return BogieFunctionality.BogieRotationInteraction(state, level, pos, player, hand);
    }
}
