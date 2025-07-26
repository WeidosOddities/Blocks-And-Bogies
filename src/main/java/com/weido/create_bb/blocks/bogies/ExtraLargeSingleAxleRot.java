package com.weido.create_bb.blocks.bogies;

import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.weido.create_bb.blocks.sizes.rotation.RotatableExtraLargeBogieBlock;
import com.weido.create_bb.registry.BogieStyles;
import net.minecraft.world.phys.Vec3;

public class ExtraLargeSingleAxleRot extends RotatableExtraLargeBogieBlock {
    public ExtraLargeSingleAxleRot(Properties props) {
        super(props, BogieStyles.SINGLE_AXLE_LONG, BogeySizes.LARGE);
    }

    @Override
    public Vec3 getConnectorAnchorOffset() {
        return new Vec3(0, 7/32f, 32/32f);
    }
}