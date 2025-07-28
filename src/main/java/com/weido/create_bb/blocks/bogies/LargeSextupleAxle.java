package com.weido.create_bb.blocks.bogies;

import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.weido.create_bb.blocks.sizes.LargeBogieBlock;
import com.weido.create_bb.registry.BogieStyles;
import net.minecraft.world.phys.Vec3;

public class LargeSextupleAxle extends LargeBogieBlock {
    public LargeSextupleAxle(Properties props) {
        super(props, BogieStyles.QUINTUPLE_AXLE_PISTONLESS, BogeySizes.SMALL);
    }
    @Override
    public Vec3 getConnectorAnchorOffset() {
        return new Vec3(0, 7/32f, 172/32f);
    }
}
