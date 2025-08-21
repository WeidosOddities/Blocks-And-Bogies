package com.weido.create_bb.data;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import com.weido.create_bb.blocks.BBBogieBlock;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public class BuilderTransformers {

    public static <B extends BBBogieBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> bogie() {
        return b -> b.initialProperties(SharedProperties::softMetal)
            .properties(p -> p.sound(SoundType.NETHERITE_BLOCK))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(pickaxeOnly())
            .blockstate((c, p) -> BlockStateGen.horizontalAxisBlock(c, p, s -> p.models()
            .getExistingFile(p.modLoc("block/bogie/top"))))
            .loot((p, l) -> p.dropOther(l, AllBlocks.RAILWAY_CASING.get()))
            .tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag);
    }
}