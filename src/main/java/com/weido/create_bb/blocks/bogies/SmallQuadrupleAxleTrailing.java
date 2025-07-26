package com.weido.create_bb.blocks.bogies;

import com.simibubi.create.content.trains.bogey.BogeySizes;

import com.weido.create_bb.blocks.sizes.SmallBogieBlock;
import com.weido.create_bb.registry.BogieStyles;

import net.minecraft.world.phys.Vec3;

public class SmallQuadrupleAxleTrailing extends SmallBogieBlock {
    public SmallQuadrupleAxleTrailing(Properties props) {
        super(props, BogieStyles.QUADRUPLE_AXLE_TRAILING, BogeySizes.SMALL);
    }
    @Override
    public Vec3 getConnectorAnchorOffset() {
        return new Vec3(0, 7/32f, 48/32f);
    }
}
