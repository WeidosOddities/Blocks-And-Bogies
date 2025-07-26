package com.weido.create_bb.blocks;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.schematic.requirement.SpecialBlockItemRequirement;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlock;
import com.simibubi.create.content.trains.bogey.BogeySizes.BogeySize;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;

import com.weido.create_bb.data.BogieFunctionality;
import com.weido.create_bb.registry.BogieBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.NotNull;

public class BBBogieBlock extends AbstractBogeyBlock <BBBogieBlockEntity>
    implements IBE<BBBogieBlockEntity>, ProperWaterloggedBlock, SpecialBlockItemRequirement {

    private final BogeyStyle defaultStyle;

    protected BBBogieBlock(Properties props, BogeyStyle defaultStyle, BogeySize size) {
        super(props, size);
        this.defaultStyle = defaultStyle;
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    protected ItemInteractionResult onInteractWithBogey(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
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
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockState state, @NotNull HitResult target, @NotNull LevelReader level, @NotNull BlockPos pos, @NotNull Player player) {
        return AllBlocks.RAILWAY_CASING.asStack();
    }

    @Override
    public Class<BBBogieBlockEntity> getBlockEntityClass() {
        return BBBogieBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends BBBogieBlockEntity> getBlockEntityType() {
        return BogieBlockEntities.BOGEY.get();
    }
}
