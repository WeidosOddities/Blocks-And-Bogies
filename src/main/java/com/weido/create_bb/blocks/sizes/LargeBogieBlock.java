package com.weido.create_bb.blocks.sizes;

import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;

import com.weido.create_bb.blocks.BBBogieBlock;

public class LargeBogieBlock extends BBBogieBlock {
    protected LargeBogieBlock(Properties props, BogeyStyle defaultStyle, BogeySizes.BogeySize size) {
        super(props, defaultStyle, size);
    }
    @Override
    public double getWheelRadius() { return 12.5 / 16d; }
}
