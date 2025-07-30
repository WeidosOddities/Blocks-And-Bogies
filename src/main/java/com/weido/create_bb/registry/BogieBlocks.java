package com.weido.create_bb.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.weido.create_bb.BlocksBogies;
import com.weido.create_bb.blocks.bogies.*;
import com.weido.create_bb.data.BuilderTransformers;
import net.minecraft.world.level.material.MapColor;

public class BogieBlocks {
    public static final CreateRegistrate REGISTRATE = BlocksBogies.registrate();

    public static final BlockEntry<LargeSingleAxleRot> L_020_ROT =
        REGISTRATE.block("l_020_rot", LargeSingleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeDoubleAxleRot> L_040_ROT =
        REGISTRATE.block("l_040_rot", LargeDoubleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeTripleAxleRot> L_060_ROT =
        REGISTRATE.block("l_060_rot", LargeTripleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeQuadrupleAxleRot> L_080_ROT =
        REGISTRATE.block("l_080_rot", LargeQuadrupleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeQuintupleAxleRot> L_0100_ROT =
        REGISTRATE.block("l_0100_rot", LargeQuintupleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeSextupleAxleRot> L_0120_ROT =
        REGISTRATE.block("l_0120_rot", LargeSextupleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<ExtraLargeSingleAxleRot> XL_020_ROT =
        REGISTRATE.block("xl_020_rot", ExtraLargeSingleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<ExtraLargeDoubleAxleRot> XL_040_ROT =
        REGISTRATE.block("xl_040_rot", ExtraLargeDoubleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<ExtraLargeTripleAxleRot> XL_060_ROT =
        REGISTRATE.block("xl_060_rot", ExtraLargeTripleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<ExtraLargeQuadrupleAxleRot> XL_080_ROT =
        REGISTRATE.block("xl_080_rot", ExtraLargeQuadrupleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<ExtraLargeQuintupleAxleRot> XL_0100_ROT =
        REGISTRATE.block("xl_0100_rot", ExtraLargeQuintupleAxleRot::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeSingleAxle> L_020 =
        REGISTRATE.block("l_020", LargeSingleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeDoubleAxle> L_040 =
        REGISTRATE.block("l_040", LargeDoubleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeTripleAxle> L_060 =
        REGISTRATE.block("l_060", LargeTripleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeQuadrupleAxle> L_080 =
        REGISTRATE.block("l_080", LargeQuadrupleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeQuintupleAxle> L_0100 =
        REGISTRATE.block("l_0100", LargeQuintupleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<LargeSextupleAxle> L_0120 =
        REGISTRATE.block("l_0120", LargeSextupleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<ExtraLargeSingleAxle> XL_020 =
        REGISTRATE.block("xl_020", ExtraLargeSingleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<ExtraLargeDoubleAxle> XL_040 =
        REGISTRATE.block("xl_040", ExtraLargeDoubleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<ExtraLargeTripleAxle> XL_060 =
        REGISTRATE.block("xl_060", ExtraLargeTripleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<ExtraLargeQuadrupleAxle> XL_080 =
        REGISTRATE.block("xl_080", ExtraLargeQuadrupleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<ExtraLargeQuintupleAxle> XL_0100 =
        REGISTRATE.block("xl_0100", ExtraLargeQuintupleAxle::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<SmallSingleAxleTrailing> S_020_TRAILING =
        REGISTRATE.block("s_020_trailing", SmallSingleAxleTrailing::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<SmallDoubleAxleTrailing> S_040_TRAILING =
        REGISTRATE.block("s_040_trailing", SmallDoubleAxleTrailing::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<SmallTripleAxleTrailing> S_060_TRAILING =
        REGISTRATE.block("s_060_trailing", SmallTripleAxleTrailing::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<SmallQuadrupleAxleTrailing> S_080_TRAILING =
        REGISTRATE.block("s_080_trailing", SmallQuadrupleAxleTrailing::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<SmallSingleAxleBogie> S_020_STANDARD =
        REGISTRATE.block("s_020_standard", SmallSingleAxleBogie::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<SmallTripleAxleStandard> S_060_STANDARD =
        REGISTRATE.block("s_060_standard", SmallTripleAxleStandard::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<SmallQuadrupleAxleStandard> S_080_STANDARD =
        REGISTRATE.block("s_080_standard", SmallQuadrupleAxleStandard::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();
    public static final BlockEntry<SmallQuintupleAxleStandard> S_0100_STANDARD =
        REGISTRATE.block("s_0100_standard", SmallQuintupleAxleStandard::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static final BlockEntry<SmallSingleAxleOffset> S_020_OFFSET =
        REGISTRATE.block("s_020_offset", SmallSingleAxleOffset::new)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform((BuilderTransformers.standardBogey()))
            .register();

    public static void register() {
        BlocksBogies.LOGGER.info("Registered bogie blocks for " + BlocksBogies.MOD_NAME);
    }
}
