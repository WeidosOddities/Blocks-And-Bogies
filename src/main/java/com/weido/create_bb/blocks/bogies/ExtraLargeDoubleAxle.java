package com.weido.create_bb.blocks.bogies;

import com.simibubi.create.content.trains.bogey.BogeySizes;

import com.weido.create_bb.blocks.sizes.ExtraLargeBogieBlock;
import com.weido.create_bb.registry.BogieStyles;

import net.minecraft.world.phys.Vec3;

public class ExtraLargeDoubleAxle extends ExtraLargeBogieBlock {
    public ExtraLargeDoubleAxle(Properties props) {
        super(props, BogieStyles.DOUBLE_AXLE_PISTONLESS, BogeySizes.LARGE);
    }

    @Override
    public Vec3 getConnectorAnchorOffset() {
        return new Vec3(0, 7/32f, 68/32f);
    }
}
