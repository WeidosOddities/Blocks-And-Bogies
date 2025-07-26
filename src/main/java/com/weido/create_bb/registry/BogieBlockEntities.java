package com.weido.create_bb.registry;

import com.simibubi.create.content.trains.bogey.BogeyBlockEntityRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.weido.create_bb.BlocksBogies;
import com.weido.create_bb.blocks.BBBogieBlockEntity;

public class BogieBlockEntities {
    private static final CreateRegistrate REGISTRATE = BlocksBogies.registrate();

    public static final BlockEntityEntry<BBBogieBlockEntity> BOGEY = REGISTRATE
        .blockEntity("bogey", BBBogieBlockEntity::new)
        .renderer(() -> BogeyBlockEntityRenderer::new)
        .validBlocks(
            BogieBlocks.L_020_ROT, BogieBlocks.XL_020_ROT,
            BogieBlocks.L_040_ROT, BogieBlocks.XL_040_ROT,
            BogieBlocks.L_060_ROT, BogieBlocks.XL_060_ROT,
            BogieBlocks.L_080_ROT, BogieBlocks.XL_080_ROT,
            BogieBlocks.L_0100_ROT, BogieBlocks.XL_0100_ROT,

            BogieBlocks.L_020, BogieBlocks.XL_020,
            BogieBlocks.L_040, BogieBlocks.XL_040,
            BogieBlocks.L_060, BogieBlocks.XL_060,
            BogieBlocks.L_080, BogieBlocks.XL_080,
            BogieBlocks.L_0100, BogieBlocks.XL_0100,

            BogieBlocks.S_020_TRAILING, BogieBlocks.S_040_TRAILING, BogieBlocks.S_060_TRAILING, BogieBlocks.S_080_TRAILING, BogieBlocks.S_0100_STANDARD,
            BogieBlocks.S_020_OFFSET, BogieBlocks.S_020_STANDARD, BogieBlocks.S_060_STANDARD, BogieBlocks.S_080_STANDARD)
        .register();

    public static void register() {
        BlocksBogies.LOGGER.info("Registered block entities for " + BlocksBogies.MOD_NAME);
    }
}
