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

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public class BuilderTransformers {

    public static <B extends BBBogieBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> sharedBogey() {
        return b -> b.initialProperties(SharedProperties::softMetal)
            .properties(p -> p.sound(SoundType.NETHERITE_BLOCK))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .transform(pickaxeOnly())
            .blockstate((c, p) -> BlockStateGen.horizontalAxisBlock(c, p, s -> p.models()
            .getExistingFile(p.modLoc("block/bogie/top"))))
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag);
    }
}