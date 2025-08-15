package com.weido.create_bb.mixin;

import com.railwayteam.railways.content.custom_bogeys.blocks.base.CRBogeyBlock;
import com.railwayteam.railways.content.custom_bogeys.blocks.base.be.CRBogeyBlockEntity;
import com.railwayteam.railways.registry.CRBlockEntities;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.schematic.requirement.SpecialBlockItemRequirement;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlock;
import com.simibubi.create.content.trains.bogey.BogeySizes.BogeySize;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import com.weido.create_bb.data.BogieFunctionality;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CRBogeyBlock.class)
public abstract class CRBogeyBlockMixin extends AbstractBogeyBlock<CRBogeyBlockEntity>
        implements IBE<CRBogeyBlockEntity>, ProperWaterloggedBlock, SpecialBlockItemRequirement {

    private final BogeyStyle defaultStyle;

    protected CRBogeyBlockMixin(Properties props, BogeyStyle defaultStyle, BogeySize size) {
        super(props, size);
        this.defaultStyle = defaultStyle;
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    protected InteractionResult onInteractWithBogey(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return BogieFunctionality.BogeyMenuInteraction(level, pos, player, hand);
    }

    @Override
    public TrackMaterial.TrackType getTrackType(BogeyStyle style) {
        return TrackMaterial.TrackType.STANDARD;
    }

    @Override
    public double getWheelPointSpacing() {
        return 2;
    }

    @Override
    public double getWheelRadius() {
        return 6.5 / 16d;
    }

    @Override
    public Vec3 getConnectorAnchorOffset() {
        return new Vec3(0, 7 / 32f, 1);
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        return defaultStyle;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return AllBlocks.RAILWAY_CASING.asStack();
    }

    @Override
    public Class<CRBogeyBlockEntity> getBlockEntityClass() {
        return CRBogeyBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CRBogeyBlockEntity> getBlockEntityType() {
        return CRBlockEntities.BOGEY.get();
    }

}