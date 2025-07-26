package com.weido.create_bb.blocks;

import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.simibubi.create.content.trains.bogey.BogeyStyle;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BBBogieBlockEntity extends AbstractBogeyBlockEntity {
    public BBBogieBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        if (getBlockState().getBlock() instanceof BBBogieBlock bogieBlock)
            return bogieBlock.getDefaultStyle();
        return AllBogeyStyles.STANDARD;
    }

}
