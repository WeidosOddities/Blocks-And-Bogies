package com.weido.create_bb.registry;

import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.content.trains.bogey.BogeyStyle.SizeRenderer;
import com.simibubi.create.content.trains.bogey.StandardBogeyRenderer;
import com.simibubi.create.content.trains.bogey.StandardBogeyVisual;
import com.weido.create_bb.BlocksBogies;
import com.weido.create_bb.data.menu.Entry.*;
import com.weido.create_bb.renderers.gearless.*;
import com.weido.create_bb.renderers.pistonless.*;
import com.weido.create_bb.renderers.walschaerts.*;
import com.weido.create_bb.renderers.scotchyoke.*;
import com.weido.create_bb.renderers.standard.*;
import com.weido.create_bb.renderers.trailing.*;
import com.weido.create_bb.visuals.gearless.*;
import com.weido.create_bb.visuals.pistonless.*;
import com.weido.create_bb.visuals.walschaerts.*;
import com.weido.create_bb.visuals.scotchyoke.*;
import com.weido.create_bb.visuals.standard.*;
import com.weido.create_bb.visuals.trailing.*;
import net.minecraft.network.chat.Component;

import static com.weido.create_bb.data.menu.Entry.StyleEntry.*;

public class BogieStyles {
    public static final BogeyStyle SINGLE_AXLE_TRAILING
        = builder("single_axle_trailing").displayName(Component.translatable("create_bb.bogies.style.single_axle_trailing"))
        .size(BogeySizes.SMALL, BogieBlocks.S_020_TRAILING, () -> () -> new SizeRenderer(new SingleAxleTrailingRenderer.SingleAxleSmallTrailing(), SingleAxleTrailingVisual.SingleAxleSmallTrailing::new))
        .build();

    public static final BogeyStyle DOUBLE_AXLE_TRAILING
        = builder("double_axle_trailing").displayName(Component.translatable("create_bb.bogies.style.double_axle_trailing"))
        .size(BogeySizes.SMALL, BogieBlocks.S_040_TRAILING, () -> () -> new SizeRenderer(new DoubleAxleTrailingRenderer.DoubleAxleSmallTrailing(), DoubleAxleTrailingVisual.DoubleAxleSmallTrailing::new))
        .build();

    public static final BogeyStyle TRIPLE_AXLE_TRAILING
        = builder("triple_axle_trailing").displayName(Component.translatable("create_bb.bogies.style.triple_axle_trailing"))
        .size(BogeySizes.SMALL, BogieBlocks.S_060_TRAILING, () -> () -> new SizeRenderer(new TripleAxleTrailingRenderer.TripleAxleSmallTrailing(), TripleAxleTrailingVisual.TripleAxleSmallTrailing::new))
        .build();

    public static final BogeyStyle QUADRUPLE_AXLE_TRAILING
        = builder("quadruple_axle_trailing").displayName(Component.translatable("create_bb.bogies.style.quadruple_axle_trailing"))
        .size(BogeySizes.SMALL, BogieBlocks.S_080_TRAILING, () -> () -> new SizeRenderer(new QuadrupleAxleTrailingRenderer.QuadrupleAxleSmallTrailing(), QuadrupleAxleTrailingVisual.QuadrupleAxleSmallTrailing::new))
        .build();

    public static final BogeyStyle SINGLE_AXLE_OFFSET
        = builder("single_axle_offset").displayName(Component.translatable("create_bb.bogies.style.single_axle_offset"))
        .size(BogeySizes.SMALL, BogieBlocks.S_020_OFFSET, () -> () -> new SizeRenderer(new SingleAxleBogieRenderer.SingleAxleSmallOffset(), SingleAxleBogieVisual.SingleAxleSmallOffset::new))
        .build();

    public static final BogeyStyle SINGLE_AXLE_BOGIE
        = builder("single_axle_bogie").displayName(Component.translatable("create_bb.bogies.style.single_axle_bogie"))
        .size(BogeySizes.SMALL, BogieBlocks.S_020_STANDARD, () -> () -> new SizeRenderer(new SingleAxleBogieRenderer.SingleAxleSmallBogie(), SingleAxleBogieVisual.SingleAxleSmallBogie::new))
        .build();

    public static final BogeyStyle TRIPLE_AXLE_BOGIE
        = builder("triple_axle_bogie").displayName(Component.translatable("create_bb.bogies.style.triple_axle_bogie"))
        .size(BogeySizes.SMALL, BogieBlocks.S_060_STANDARD, () -> () -> new SizeRenderer(new TripleAxleBogieRenderer.TripleAxleSmallBogie(), TripleAxleBogieVisual.TripleAxleSmallBogie::new))
        .build();

    public static final BogeyStyle QUADRUPLE_AXLE_BOGIE
        = builder("quadruple_axle_bogie").displayName(Component.translatable("create_bb.bogies.style.quadruple_axle_bogie"))
        .size(BogeySizes.SMALL, BogieBlocks.S_080_STANDARD, () -> () -> new SizeRenderer(new QuadrupleAxleBogieRenderer.QuadrupleAxleSmallBogie(), QuadrupleAxleBogieVisual.QuadrupleAxleSmallBogie::new))
        .build();

    public static final BogeyStyle QUINTUPLE_AXLE_BOGIE
        = builder("quintuple_axle_bogie").displayName(Component.translatable("create_bb.bogies.style.quintuple_axle_bogie"))
        .size(BogeySizes.SMALL, BogieBlocks.S_0100_STANDARD, () -> () -> new SizeRenderer(new QuintupleAxleBogieRenderer.SmallQuintupleAxleBogie(), QuintupleAxleBogieVisual.SmallQuintupleAxleBogie::new))
        .build();

//Pistonless
    public static final BogeyStyle SINGLE_AXLE_PISTONLESS
        = builder("single_axle_pistonless").displayName(Component.translatable("create_bb.bogies.style.single_axle_pistonless"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_020, () -> () -> new SizeRenderer(new SingleAxlePistonlessRenderer.SingleAxleExtraLargePistonless(), SingleAxlePistonlessVisual.SingleAxleExtraLargePistonless::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_020, () -> () -> new SizeRenderer(new SingleAxlePistonlessRenderer.SingleAxleLargePistonless(), SingleAxlePistonlessVisual.SingleAxleLargePistonless::new))
        .build();

    public static final BogeyStyle DOUBLE_AXLE_PISTONLESS
        = builder("double_axle_pistonless").displayName(Component.translatable("create_bb.bogies.style.double_axle_pistonless"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_040, () -> () -> new SizeRenderer(new DoubleAxlePistonlessRenderer.DoubleAxleExtraLargePistonless(), DoubleAxlePistonlessVisual.DoubleAxleExtraLargePistonless::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_040, () -> () -> new SizeRenderer(new DoubleAxlePistonlessRenderer.DoubleAxleLargePistonless(), DoubleAxlePistonlessVisual.DoubleAxleLargePistonless::new))
        .build();

    public static final BogeyStyle TRIPLE_AXLE_EXTENDED_PISTONLESS
            = builder("triple_axle_extended_pistonless").displayName(Component.translatable("create_bb.bogies.style.triple_axle_extended_pistonless"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_060, () -> () -> new SizeRenderer(new TripleAxleExtendedPistonlessRenderer.TripleAxleExtraLargeExtendedPistonless(), TripleAxleExtendedPistonlessVisual.TripleAxleExtraLargeExtendedPistonless::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_060, () -> () -> new SizeRenderer(new TripleAxleExtendedPistonlessRenderer.TripleAxleLargeExtendedPistonless(), TripleAxleExtendedPistonlessVisual.TripleAxleLargeExtendedPistonless::new))
            .build();

    public static final BogeyStyle TRIPLE_AXLE_PISTONLESS
        = builder("triple_axle_pistonless").displayName(Component.translatable("create_bb.bogies.style.triple_axle_pistonless"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_060, () -> () -> new SizeRenderer(new TripleAxlePistonlessRenderer.TripleAxleExtraLargePistonless(), TripleAxlePistonlessVisual.TripleAxleExtraLargePistonless::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_060, () -> () -> new SizeRenderer(new TripleAxlePistonlessRenderer.TripleAxleLargePistonless(), TripleAxlePistonlessVisual.TripleAxleLargePistonless::new))
        .build();

    public static final BogeyStyle QUADRUPLE_AXLE_PISTONLESS
        = builder("quadruple_axle_pistonless").displayName(Component.translatable("create_bb.bogies.style.quadruple_axle_pistonless"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_080, () -> () -> new SizeRenderer(new QuadrupleAxlePistonlessRenderer.QuadrupleAxleExtraLargePistonless(), QuadrupleAxlePistonlessVisual.QuadrupleAxleExtraLargePistonless::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_080, () -> () -> new SizeRenderer(new QuadrupleAxlePistonlessRenderer.QuadrupleAxleLargePistonless(), QuadrupleAxlePistonlessVisual.QuadrupleAxleLargePistonless::new))
        .build();

    public static final BogeyStyle QUINTUPLE_AXLE_PISTONLESS
        = builder("quintuple_axle_pistonless_l").displayName(Component.translatable("create_bb.bogies.style.quintuple_axle_pistonless"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_0100, () -> () -> new SizeRenderer(new QuintupleAxlePistonlessRenderer.QuintupleAxleExtraLargePistonless(), QuintupleAxlePistonlessVisual.QuintupleAxleExtraLargePistonless::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_0100, () -> () -> new SizeRenderer(new QuintupleAxlePistonlessRenderer.QuintupleAxleLargePistonless(), QuintupleAxlePistonlessVisual.QuintupleAxleLargePistonless::new))
        .build();

//Scotch Yoke
    public static final BogeyStyle SINGLE_AXLE_SCOTCH_YOKE
        = builder("single_axle_scotch_yoke").displayName(Component.translatable("create_bb.bogies.style.single_axle_scotch_yoke"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_020, () -> () -> new SizeRenderer(new SingleAxleScotchYokeRenderer.SingleAxleExtraLargeYokeRenderer(), SingleAxleScotchYokeVisual.SingleAxleExtraLargeScotchYoke::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_020, () -> () -> new SizeRenderer(new StandardBogeyRenderer.Large(), StandardBogeyVisual.Large::new))
        .build();

    public static final BogeyStyle DOUBLE_AXLE_SCOTCH_YOKE
        = builder("double_axle_scotch_yoke").displayName(Component.translatable("create_bb.bogies.style.double_axle_scotch_yoke"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_040, () -> () -> new SizeRenderer(new DoubleAxleScotchYokeRenderer.DoubleAxleExtraLargeScotchYoke(), DoubleAxleScotchYokeVisual.DoubleAxleExtraLargeScotchYoke::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_040, () -> () -> new SizeRenderer(new DoubleAxleScotchYokeRenderer.DoubleAxleLargeScotchYoke(), DoubleAxleScotchYokeVisual.DoubleAxleLargeScotchYoke::new))
        .build();

    public static final BogeyStyle TRIPLE_AXLE_SCOTCH_YOKE
        = builder("triple_axle_scotch_yoke").displayName(Component.translatable("create_bb.bogies.style.triple_axle_scotch_yoke"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_060, () -> () -> new SizeRenderer(new TripleAxleScotchYokeRenderer.TripleAxleExtraLargeScotchYoke(), TripleAxleScotchYokeVisual.TripleAxleExtraLargeScotchYoke::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_060, () -> () -> new SizeRenderer(new TripleAxleScotchYokeRenderer.TripleAxleLargeScotchYoke(), TripleAxleScotchYokeVisual.TripleAxleLargeScotchYoke::new))
        .build();

    public static final BogeyStyle QUADRUPLE_AXLE_SCOTCH_YOKE
        = builder("quadruple_axle_scotch_yoke").displayName(Component.translatable("create_bb.bogies.style.quadruple_axle_scotch_yoke"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_080, () -> () -> new SizeRenderer(new QuadrupleAxleScotchYokeRenderer.QuadrupleAxleExtraLargeScotchYoke(), QuadrupleAxleScotchYokeVisual.QuadrupleAxleExtraLargeScotchYoke::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_080, () -> () -> new SizeRenderer(new QuadrupleAxleScotchYokeRenderer.QuadrupleAxleLargeScotchYoke(), QuadrupleAxleScotchYokeVisual.QuadrupleAxleLargeScotchYoke::new))
        .build();

    public static final BogeyStyle QUINTUPLE_AXLE_SCOTCH_YOKE
        = builder("quintuple_axle_scotch_yoke").displayName(Component.translatable("create_bb.bogies.style.quintuple_axle_scotch_yoke"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_0100, () -> () -> new SizeRenderer(new QuintupleAxleScotchYokeRenderer.QuintupleAxleExtraLargeScotchYoke(), QuintupleAxleScotchYokeVisual.QuintupleAxleExtraLargeScotchYoke::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_0100, () -> () -> new SizeRenderer(new QuintupleAxleScotchYokeRenderer.QuintupleAxleLargeScotchYoke(), QuintupleAxleScotchYokeVisual.QuintupleAxleLargeScotchYoke::new))
        .build();
//Long Gearless
    public static final BogeyStyle SINGLE_AXLE_LONG
        = builder("single_axle_long").displayName(Component.translatable("create_bb.bogies.style.single_axle_long"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_020_ROT, () -> () -> new SizeRenderer(new SingleAxleLongRenderer.SingleAxleExtraLargeLong(), SingleAxleLongVisual.SingleAxleExtraLargeLong::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_020_ROT, () -> () -> new SizeRenderer(new SingleAxleLongRenderer.SingleAxleLargeLong(), SingleAxleLongVisual.SingleAxleLargeLong::new))
        .build();

    public static final BogeyStyle DOUBLE_AXLE_LONG
        = builder("double_axle_long").displayName(Component.translatable("create_bb.bogies.style.double_axle_long"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_040_ROT, () -> () -> new SizeRenderer(new DoubleAxleLongRenderer.DoubleAxleExtraLargeLong(), DoubleAxleLongVisual.DoubleAxleExtraLargeLong::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_040_ROT, () -> () -> new SizeRenderer(new DoubleAxleLongRenderer.DoubleAxleLargeLong(), DoubleAxleLongVisual.DoubleAxleLargeLong::new))
        .build();

    public static final BogeyStyle TRIPLE_AXLE_EXTENDED_LONG
            = builder("triple_axle_extended_long").displayName(Component.translatable("create_bb.bogies.style.triple_axle_extended_long"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_060_ROT, () -> () -> new SizeRenderer(new TripleAxleExtendedLongRenderer.TripleAxleExtraLargeExtendedLong(), TripleAxleExtendedLongVisual.TripleAxleExtraLargeExtendedLong::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_060_ROT, () -> () -> new SizeRenderer(new TripleAxleExtendedLongRenderer.TripleAxleLargeExtendedLong(), TripleAxleExtendedLongVisual.TripleAxleLargeExtendedLong::new))
            .build();

    public static final BogeyStyle TRIPLE_AXLE_LONG
        = builder("triple_axle_long").displayName(Component.translatable("create_bb.bogies.style.triple_axle_long"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_060_ROT, () -> () -> new SizeRenderer(new TripleAxleLongRenderer.TripleAxleExtraLargeLong(), TripleAxleLongVisual.TripleAxleExtraLargeLong::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_060_ROT, () -> () -> new SizeRenderer(new TripleAxleLongRenderer.TripleAxleLargeLong(), TripleAxleLongVisual.TripleAxleLargeLong::new))
        .build();

    public static final BogeyStyle QUADRUPLE_AXLE_LONG
        = builder("quadruple_axle_long").displayName(Component.translatable("create_bb.bogies.style.quadruple_axle_long"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_080_ROT, () -> () -> new SizeRenderer(new QuadrupleAxleLongRenderer.QuadrupleAxleExtraLargeLong(), QuadrupleAxleLongVisual.QuadrupleAxleExtraLargeLong::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_080_ROT, () -> () -> new SizeRenderer(new QuadrupleAxleLongRenderer.QuadrupleAxleLargeLong(), QuadrupleAxleLongVisual.QuadrupleAxleLargeLong::new))
        .build();

    public static final BogeyStyle QUINTUPLE_AXLE_LONG
        = builder("quintuple_axle_long").displayName(Component.translatable("create_bb.bogies.style.quintuple_axle_long"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_0100_ROT, () -> () -> new SizeRenderer(new QuintupleAxleLongRenderer.QuintupleAxleExtraLargeLong(), QuintupleAxleLongVisual.QuintupleAxleExtraLargeLong::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_0100_ROT, () -> () -> new SizeRenderer(new QuintupleAxleLongRenderer.QuintupleAxleLargeLong(), QuintupleAxleLongVisual.QuintupleAxleLargeLong::new))
        .build();
    //Short Gearless
    public static final BogeyStyle DOUBLE_AXLE_SHORT
        = builder("double_axle_short").displayName(Component.translatable("create_bb.bogies.style.double_axle_short"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_040_ROT, () -> () -> new SizeRenderer(new DoubleAxleShortRenderer.DoubleAxleExtraLargeShort(), DoubleAxleShortVisual.DoubleAxleExtraLargeShort::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_040_ROT, () -> () -> new SizeRenderer(new DoubleAxleShortRenderer.DoubleAxleLargeShort(), DoubleAxleShortVisual.DoubleAxleLargeShort::new))
        .build();

    public static final BogeyStyle TRIPLE_AXLE_EXTENDED_SHORT
            = builder("triple_axle_extended_short").displayName(Component.translatable("create_bb.bogies.style.triple_axle_extended_short"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_060_ROT, () -> () -> new SizeRenderer(new TripleAxleExtendedShortRenderer.TripleAxleExtraLargeExtendedShort(), TripleAxleExtendedShortVisual.TripleAxleExtraLargeExtendedShort::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_060_ROT, () -> () -> new SizeRenderer(new TripleAxleExtendedShortRenderer.TripleAxleLargeExtendedShort(), TripleAxleExtendedShortVisual.TripleAxleLargeExtendedShort::new))
            .build();

    public static final BogeyStyle TRIPLE_AXLE_SHORT
        = builder("triple_axle_short").displayName(Component.translatable("create_bb.bogies.style.triple_axle_short"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_060_ROT, () -> () -> new SizeRenderer(new TripleAxleShortRenderer.TripleAxleExtraLargeShort(), TripleAxleShortVisual.TripleAxleExtraLargeShort::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_060_ROT, () -> () -> new SizeRenderer(new TripleAxleShortRenderer.TripleAxleLargeShort(), TripleAxleShortVisual.TripleAxleLargeShort::new))
        .build();

    public static final BogeyStyle QUADRUPLE_AXLE_SHORT
        = builder("quadruple_axle_short").displayName(Component.translatable("create_bb.bogies.style.quadruple_axle_short"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_080_ROT, () -> () -> new SizeRenderer(new QuadrupleAxleShortRenderer.QuadrupleAxleExtraLargeShort(), QuadrupleAxleShortVisual.QuadrupleAxleExtraLargeShort::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_080_ROT, () -> () -> new SizeRenderer(new QuadrupleAxleShortRenderer.QuadrupleAxleLargeShort(), QuadrupleAxleShortVisual.QuadrupleAxleLargeShort::new))
        .build();

    public static final BogeyStyle QUINTUPLE_AXLE_SHORT
        = builder("quintuple_axle_short").displayName(Component.translatable("create_bb.bogies.style.quintuple_axle_short"))
        .size(BogeySizes.LARGE, BogieBlocks.XL_0100_ROT, () -> () -> new SizeRenderer(new QuintupleAxleShortRenderer.QuintupleAxleExtraLargeShort(), QuintupleAxleShortVisual.QuintupleAxleExtraLargeShort::new))
        .size(BogeySizes.SMALL, BogieBlocks.L_0100_ROT, () -> () -> new SizeRenderer(new QuintupleAxleShortRenderer.QuintupleAxleLargeShort(), QuintupleAxleShortVisual.QuintupleAxleLargeShort::new))
        .build();

    public static final BogeyStyle SINGLE_AXLE_WALSCHAERTS_LONG
            = builder("single_axle_walschaerts_long").displayName(Component.translatable("create_bb.bogies.style.single_axle_walschaerts_long"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_020_ROT, () -> () -> new SizeRenderer(new SingleAxleWalschaertsLongRenderer.SingleAxleExtraLargeWalschaertsLong(), SingleAxleWalschaertsLongVisual.SingleAxleExtraLargeWalschaertsLong::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_020_ROT, () -> () -> new SizeRenderer(new SingleAxleWalschaertsLongRenderer.SingleAxleLargeWalschaertsLong(), SingleAxleWalschaertsLongVisual.SingleAxleLargeWalschaertsLong::new))
            .build();

    public static final BogeyStyle DOUBLE_AXLE_WALSCHAERTS_LONG
            = builder("double_axle_walschaerts_long").displayName(Component.translatable("create_bb.bogies.style.double_axle_walschaerts_long"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_040_ROT, () -> () -> new SizeRenderer(new DoubleAxleWalschaertsLongRenderer.DoubleAxleExtraLargeWalschaertsLong(), DoubleAxleWalschaertsLongVisual.DoubleAxleExtraLargeWalschaertsLong::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_040_ROT, () -> () -> new SizeRenderer(new DoubleAxleWalschaertsLongRenderer.DoubleAxleLargeWalschaertsLong(), DoubleAxleWalschaertsLongVisual.DoubleAxleLargeWalschaertsLong::new))
            .build();

    public static final BogeyStyle TRIPLE_AXLE_WALSCHAERTS_EXTENDED_LONG
            = builder("triple_axle_walschaerts_extended_long").displayName(Component.translatable("create_bb.bogies.style.triple_axle_walschaerts_extended_long"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_060_ROT, () -> () -> new SizeRenderer(new TripleAxleExtendedWalschaertsLongRenderer.TripleAxleExtraLargeExtendedWalschaertsLong(), TripleAxleExtendedWalschaertsLongVisual.TripleAxleExtraLargeExtendedWalschaertsLong::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_060_ROT, () -> () -> new SizeRenderer(new TripleAxleExtendedWalschaertsLongRenderer.TripleAxleLargeExtendedWalschaertsLong(), TripleAxleExtendedWalschaertsLongVisual.TripleAxleLargeExtendedWalschaertsLong::new))
            .build();

    public static final BogeyStyle TRIPLE_AXLE_WALSCHAERTS_LONG
            = builder("triple_axle_walschaerts_long").displayName(Component.translatable("create_bb.bogies.style.triple_axle_walschaerts_long"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_060_ROT, () -> () -> new SizeRenderer(new TripleAxleWalschaertsLongRenderer.TripleAxleExtraLargeWalschaertsLong(), TripleAxleWalschaertsLongVisual.TripleAxleExtraLargeWalschaertsLong::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_060_ROT, () -> () -> new SizeRenderer(new TripleAxleWalschaertsLongRenderer.TripleAxleLargeWalschaertsLong(), TripleAxleWalschaertsLongVisual.TripleAxleLargeWalschaertsLong::new))
            .build();

    public static final BogeyStyle QUADRUPLE_AXLE_WALSCHAERTS_LONG
            = builder("quadruple_axle_walschaerts_long").displayName(Component.translatable("create_bb.bogies.style.quadruple_axle_walschaerts_long"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_080_ROT, () -> () -> new SizeRenderer(new QuadrupleAxleWalschaertsLongRenderer.QuadrupleAxleExtraLargeWalschaertsLong(), QuadrupleAxleWalschaertsLongVisual.QuadrupleAxleExtraLargeWalschaertsLong::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_080_ROT, () -> () -> new SizeRenderer(new QuadrupleAxleWalschaertsLongRenderer.QuadrupleAxleLargeWalschaertsLong(), QuadrupleAxleWalschaertsLongVisual.QuadrupleAxleLargeWalschaertsLong::new))
            .build();

    public static final BogeyStyle QUINTUPLE_AXLE_WALSCHAERTS_LONG
            = builder("quintuple_axle_walschaerts_long").displayName(Component.translatable("create_bb.bogies.style.quintuple_axle_walschaerts_long"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_0100_ROT, () -> () -> new SizeRenderer(new QuintupleAxleWalschaertsLongRenderer.QuintupleAxleExtraLargeWalschaertsLong(), QuintupleAxleWalschaertsLongVisual.QuintupleAxleExtraLargeWalschaertsLong::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_0100_ROT, () -> () -> new SizeRenderer(new QuintupleAxleWalschaertsLongRenderer.QuintupleAxleLargeWalschaertsLong(), QuintupleAxleWalschaertsLongVisual.QuintupleAxleLargeWalschaertsLong::new))
            .build();

    public static final BogeyStyle DOUBLE_AXLE_WALSCHAERTS_SHORT
            = builder("double_axle_walschaerts_short").displayName(Component.translatable("create_bb.bogies.style.double_axle_walschaerts_short"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_040_ROT, () -> () -> new SizeRenderer(new DoubleAxleWalschaertsShortRenderer.DoubleAxleExtraLargeWalschaertsShort(), DoubleAxleWalschaertsShortVisual.DoubleAxleExtraLargeWalschaertsShort::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_040_ROT, () -> () -> new SizeRenderer(new DoubleAxleWalschaertsShortRenderer.DoubleAxleLargeWalschaertsShort(), DoubleAxleWalschaertsShortVisual.DoubleAxleLargeWalschaertsShort::new))
            .build();

    public static final BogeyStyle TRIPLE_AXLE_WALSCHAERTS_EXTENDED_SHORT
            = builder("triple_axle_walschaerts_extended_short").displayName(Component.translatable("create_bb.bogies.style.triple_axle_walschaerts_extended_short"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_060_ROT, () -> () -> new SizeRenderer(new TripleAxleExtendedWalschaertsShortRenderer.TripleAxleExtraLargeExtendedWalschaertsShort(), TripleAxleExtendedWalschaertsShortVisual.TripleAxleExtraLargeExtendedWalschaertsShort::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_060_ROT, () -> () -> new SizeRenderer(new TripleAxleExtendedWalschaertsShortRenderer.TripleAxleLargeExtendedWalschaertsShort(), TripleAxleExtendedWalschaertsShortVisual.TripleAxleLargeExtendedWalschaertsShort::new))
            .build();

    public static final BogeyStyle TRIPLE_AXLE_WALSCHAERTS_SHORT
            = builder("triple_axle_walschaerts_short").displayName(Component.translatable("create_bb.bogies.style.triple_axle_walschaerts_short"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_060_ROT, () -> () -> new SizeRenderer(new TripleAxleWalschaertsShortRenderer.TripleAxleExtraLargeWalschaertsShort(), TripleAxleWalschaertsShortVisual.TripleAxleExtraLargeWalschaertsShort::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_060_ROT, () -> () -> new SizeRenderer(new TripleAxleWalschaertsShortRenderer.TripleAxleLargeWalschaertsShort(), TripleAxleWalschaertsShortVisual.TripleAxleLargeWalschaertsShort::new))
            .build();

    public static final BogeyStyle QUADRUPLE_AXLE_WALSCHAERTS_SHORT
            = builder("quadruple_axle_walschaerts_short").displayName(Component.translatable("create_bb.bogies.style.quadruple_axle_walschaerts_short"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_080_ROT, () -> () -> new SizeRenderer(new QuadrupleAxleWalschaertsShortRenderer.QuadrupleAxleExtraLargeWalschaertsShort(), QuadrupleAxleWalschaertsShortVisual.QuadrupleAxleExtraLargeWalschaertsShort::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_080_ROT, () -> () -> new SizeRenderer(new QuadrupleAxleWalschaertsShortRenderer.QuadrupleAxleLargeWalschaertsShort(), QuadrupleAxleWalschaertsShortVisual.QuadrupleAxleLargeWalschaertsShort::new))
            .build();

    public static final BogeyStyle QUINTUPLE_AXLE_WALSCHAERTS_SHORT
            = builder("quintuple_axle_walschaerts_short").displayName(Component.translatable("create_bb.bogies.style.quintuple_axle_walschaerts_short"))
            .size(BogeySizes.LARGE, BogieBlocks.XL_0100_ROT, () -> () -> new SizeRenderer(new QuintupleAxleWalschaertsShortRenderer.QuintupleAxleExtraLargeWalschaertsShort(), QuintupleAxleWalschaertsShortVisual.QuintupleAxleExtraLargeWalschaertsShort::new))
            .size(BogeySizes.SMALL, BogieBlocks.L_0100_ROT, () -> () -> new SizeRenderer(new QuintupleAxleWalschaertsShortRenderer.QuintupleAxleLargeWalschaertsShort(), QuintupleAxleWalschaertsShortVisual.QuintupleAxleLargeWalschaertsShort::new))
            .build();

    private static BogeyStyle.Builder builder(String name) {
        return new BogeyStyle.Builder(BlocksBogies.asResource(name), AllBogeyStyles.STANDARD_CYCLE_GROUP);
    }

    public static final StyleEntryManager MANAGER = new StyleEntryManager();

    public static void registerBogeyStyles() {
        if (StyleEntryManager.getBogeyEntryList().isEmpty()) {
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(AllBogeyStyles.STANDARD, Variant.STANDARD, ValveGear.NONE, Type.TRUCK, 2, Length.NORMAL));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.SINGLE_AXLE_TRAILING, Variant.TRAILING, ValveGear.NONE, Type.TRUCK, 1, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.DOUBLE_AXLE_TRAILING, Variant.TRAILING, ValveGear.NONE, Type.TRUCK, 2, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_TRAILING, Variant.TRAILING, ValveGear.NONE, Type.TRUCK, 3, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUADRUPLE_AXLE_TRAILING, Variant.TRAILING, ValveGear.NONE, Type.TRUCK, 4, Length.NORMAL));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.SINGLE_AXLE_OFFSET, Variant.STANDARD, ValveGear.NONE, Type.TRUCK, 1, Length.OFFSET));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.SINGLE_AXLE_BOGIE, Variant.STANDARD, ValveGear.NONE, Type.TRUCK, 1, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_BOGIE, Variant.STANDARD, ValveGear.NONE, Type.TRUCK, 3, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUADRUPLE_AXLE_BOGIE, Variant.STANDARD, ValveGear.NONE, Type.TRUCK, 4, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUINTUPLE_AXLE_BOGIE, Variant.STANDARD, ValveGear.NONE, Type.TRUCK, 5, Length.NORMAL));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.SINGLE_AXLE_PISTONLESS, Variant.PISTONLESS, ValveGear.NONE, Type.DRIVER, 1, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.DOUBLE_AXLE_PISTONLESS, Variant.PISTONLESS, ValveGear.NONE, Type.DRIVER, 2, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_EXTENDED_PISTONLESS, Variant.PISTONLESS, ValveGear.NONE, Type.DRIVER, 3, Length.EXTENDED));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_PISTONLESS, Variant.PISTONLESS, ValveGear.NONE, Type.DRIVER, 3, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUADRUPLE_AXLE_PISTONLESS, Variant.PISTONLESS, ValveGear.NONE, Type.DRIVER, 4, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUINTUPLE_AXLE_PISTONLESS, Variant.PISTONLESS, ValveGear.NONE, Type.DRIVER, 5, Length.NORMAL));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.SINGLE_AXLE_SCOTCH_YOKE, Variant.STANDARD, ValveGear.SCOTCH_YOKE, Type.DRIVER, 1, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.DOUBLE_AXLE_SCOTCH_YOKE, Variant.STANDARD, ValveGear.SCOTCH_YOKE, Type.DRIVER, 2, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_SCOTCH_YOKE, Variant.STANDARD, ValveGear.SCOTCH_YOKE, Type.DRIVER, 3, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUADRUPLE_AXLE_SCOTCH_YOKE, Variant.STANDARD, ValveGear.SCOTCH_YOKE, Type.DRIVER, 4, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUINTUPLE_AXLE_SCOTCH_YOKE, Variant.STANDARD, ValveGear.SCOTCH_YOKE, Type.DRIVER, 5, Length.NORMAL));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.SINGLE_AXLE_LONG, Variant.LONG, ValveGear.NONE, Type.DRIVER, 1, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.DOUBLE_AXLE_LONG, Variant.LONG, ValveGear.NONE, Type.DRIVER, 2, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_EXTENDED_LONG, Variant.LONG, ValveGear.NONE, Type.DRIVER, 3, Length.EXTENDED));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_LONG, Variant.LONG, ValveGear.NONE, Type.DRIVER, 3, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUADRUPLE_AXLE_LONG, Variant.LONG, ValveGear.NONE, Type.DRIVER, 4, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUINTUPLE_AXLE_LONG, Variant.LONG, ValveGear.NONE, Type.DRIVER, 5, Length.NORMAL));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.DOUBLE_AXLE_SHORT, Variant.SHORT, ValveGear.NONE, Type.DRIVER, 2, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_EXTENDED_SHORT, Variant.SHORT, ValveGear.NONE, Type.DRIVER, 3, Length.EXTENDED));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_SHORT, Variant.SHORT, ValveGear.NONE, Type.DRIVER, 3,Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUADRUPLE_AXLE_SHORT, Variant.SHORT, ValveGear.NONE, Type.DRIVER, 4, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUINTUPLE_AXLE_SHORT, Variant.SHORT, ValveGear.NONE, Type.DRIVER, 5, Length.NORMAL));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.SINGLE_AXLE_WALSCHAERTS_LONG, Variant.LONG, ValveGear.WALSCHAERTS, Type.DRIVER, 1, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.DOUBLE_AXLE_WALSCHAERTS_LONG, Variant.LONG, ValveGear.WALSCHAERTS, Type.DRIVER, 2, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_WALSCHAERTS_EXTENDED_LONG, Variant.LONG, ValveGear.WALSCHAERTS, Type.DRIVER, 3, Length.EXTENDED));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_WALSCHAERTS_LONG, Variant.LONG, ValveGear.WALSCHAERTS, Type.DRIVER, 3, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUADRUPLE_AXLE_WALSCHAERTS_LONG, Variant.LONG, ValveGear.WALSCHAERTS, Type.DRIVER, 4, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUINTUPLE_AXLE_WALSCHAERTS_LONG, Variant.LONG, ValveGear.WALSCHAERTS, Type.DRIVER, 5, Length.NORMAL));

            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.DOUBLE_AXLE_WALSCHAERTS_SHORT, Variant.SHORT, ValveGear.WALSCHAERTS, Type.DRIVER, 2, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_WALSCHAERTS_EXTENDED_SHORT, Variant.SHORT, ValveGear.WALSCHAERTS, Type.DRIVER, 3, Length.EXTENDED));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.TRIPLE_AXLE_WALSCHAERTS_SHORT, Variant.SHORT, ValveGear.WALSCHAERTS, Type.DRIVER, 3, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUADRUPLE_AXLE_WALSCHAERTS_SHORT, Variant.SHORT, ValveGear.WALSCHAERTS, Type.DRIVER, 4, Length.NORMAL));
            MANAGER.addToBogeyEntryList(StyleEntry.getOrCreate(BogieStyles.QUINTUPLE_AXLE_WALSCHAERTS_SHORT, Variant.SHORT, ValveGear.WALSCHAERTS, Type.DRIVER, 5, Length.NORMAL));
        }
    }

    public static void register() {
        BlocksBogies.LOGGER.info("Registered styles/menu selections for " + BlocksBogies.MOD_NAME);
        registerBogeyStyles();
    }
}