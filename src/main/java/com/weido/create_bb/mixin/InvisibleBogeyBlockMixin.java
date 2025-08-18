package com.weido.create_bb.mixin;

import com.google.common.collect.ImmutableSet;
import com.railwayteam.railways.content.custom_bogeys.special.invisible.InvisibleBogeyBlock;
import com.railwayteam.railways.content.custom_bogeys.special.invisible.InvisibleBogeyBlockEntity;
import com.railwayteam.railways.registry.CRBlockEntities;
import com.railwayteam.railways.registry.CRBogeyStyles;
import com.railwayteam.railways.registry.CRShapes;
import com.railwayteam.railways.registry.CRTrackMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.schematic.requirement.SpecialBlockItemRequirement;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlock;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.content.trains.entity.Carriage;
import com.simibubi.create.content.trains.entity.CarriageBogey;
import com.simibubi.create.content.trains.entity.TravellingPoint;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Set;

@Mixin(InvisibleBogeyBlock.class)
public abstract class InvisibleBogeyBlockMixin extends AbstractBogeyBlock<InvisibleBogeyBlockEntity>
        implements IBE<InvisibleBogeyBlockEntity>, ProperWaterloggedBlock, SpecialBlockItemRequirement {

    protected InvisibleBogeyBlockMixin(Properties props) {
        super(props, BogeySizes.SMALL);
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
    public boolean isOnIncompatibleTrack(Carriage carriage, boolean leading) {
        TravellingPoint point = leading ? carriage.getLeadingPoint() : carriage.getTrailingPoint();
        CarriageBogey bogey = leading ? carriage.leadingBogey() : carriage.trailingBogey();

        if (point.edge == null)
            return false;

        return point.edge.getTrackMaterial().trackType != getTrackType(bogey.getStyle())
                && point.edge.getTrackMaterial().trackType != CRTrackMaterials.CRTrackType.WIDE_GAUGE
                && point.edge.getTrackMaterial().trackType != CRTrackMaterials.CRTrackType.NARROW_GAUGE
                && point.edge.getTrackMaterial().trackType != CRTrackMaterials.CRTrackType.MONORAIL;
    }

    @Override
    public Set<TrackMaterial.TrackType> getValidPathfindingTypes(BogeyStyle style) {
        return ImmutableSet.of(getTrackType(style), CRTrackMaterials.CRTrackType.WIDE_GAUGE, CRTrackMaterials.CRTrackType.NARROW_GAUGE, CRTrackMaterials.CRTrackType.MONORAIL);
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
        return CRBogeyStyles.INVISIBLE;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state) {
        return AllBlocks.RAILWAY_CASING.asStack();
    }

    @Override
    public Class<InvisibleBogeyBlockEntity> getBlockEntityClass() {
        return InvisibleBogeyBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends InvisibleBogeyBlockEntity> getBlockEntityType() {
        return CRBlockEntities.INVISIBLE_BOGEY.get();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return CRShapes.INVISIBLE_BOGEY;
    }
}