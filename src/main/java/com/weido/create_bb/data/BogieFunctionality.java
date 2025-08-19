package com.weido.create_bb.data;

import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.weido.create_bb.blocks.BBBogieBlockEntity;
import com.weido.create_bb.data.packets.ClientBogieMenuPacket;
import com.weido.create_bb.data.packets.ServerBogieMenuPacket;
import com.weido.create_bb.registry.BogiePackets;
import net.createmod.catnip.nbt.NBTHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;

import java.util.Random;

import static com.weido.create_bb.data.Constants.BOGIE_ASSEMBLY_DIRECTION_KEY;
import static com.weido.create_bb.data.Constants.BOGIE_DIRECTION_KEY;

public class BogieFunctionality {
    public static InteractionResult BogieRotationInteraction(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand) {
        if (isInvalidInteraction(player, hand)) return InteractionResult.PASS;

        BBBogieBlockEntity be = level.getBlockEntity(pos) instanceof BBBogieBlockEntity bogie ? bogie : null;
        if (be == null) return InteractionResult.PASS;

        if (player.isShiftKeyDown()) {
            return openMenu(level, player, pos);
        }

        return rotateBogie(state, level, pos, player, be);
    }

    public static InteractionResult BogeyMenuInteraction(Level level, BlockPos pos, Player player, InteractionHand hand) {
        if (isInvalidInteraction(player, hand)) return InteractionResult.PASS;

        AbstractBogeyBlockEntity be = (AbstractBogeyBlockEntity) level.getBlockEntity(pos);
        if (be == null || !player.isShiftKeyDown()) return InteractionResult.PASS;

        return openMenu(level, player, pos);
    }

    private static boolean isInvalidInteraction(Player player, InteractionHand hand) {
        return hand != InteractionHand.MAIN_HAND || !player.getItemInHand(hand).isEmpty();
    }

    private static InteractionResult openMenu(Level level, Player player, BlockPos pos) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        if (player instanceof ServerPlayer serverPlayer) {
//            ClientBogieMenuPacket packet = new ClientBogieMenuPacket(pos);
//            BogiePackets.getChannel().sendToServer(packet);

            BogiePackets.getChannel().send(
                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                    new ServerBogieMenuPacket(pos)
            );
        }



        AllSoundEvents.SCROLL_VALUE.playOnServer(level, pos, 1, 1);
        return InteractionResult.CONSUME;
    }
    // Bogie Rotation 🗿
    private static InteractionResult rotateBogie(BlockState state, Level level, BlockPos pos, Player player, BBBogieBlockEntity be) {
        if (level.isClientSide) return InteractionResult.FAIL;

        CompoundTag bogieData = be.getBogeyData();
        Direction assemblyDirection = NBTHelper.readEnum(bogieData, BOGIE_ASSEMBLY_DIRECTION_KEY, Direction.class);

        boolean isNewBogie = !bogieData.contains(BOGIE_DIRECTION_KEY);
        boolean newDirection = isNewBogie
            ? isDirectionPositive(assemblyDirection)
            : !bogieData.getBoolean(BOGIE_DIRECTION_KEY);

        if (!bogieData.contains(BOGIE_ASSEMBLY_DIRECTION_KEY)) {
            NBTHelper.writeEnum(bogieData, BOGIE_ASSEMBLY_DIRECTION_KEY, Direction.SOUTH);
        }

        if (level instanceof ServerLevel) {
            level.sendBlockUpdated(pos, state, state, 3);
            level.blockUpdated(pos, state.getBlock());
        }

        bogieData.putBoolean(BOGIE_DIRECTION_KEY, newDirection);
        be.setBogeyData(bogieData);
        be.setChanged();

        AllSoundEvents.WRENCH_ROTATE.playOnServer(level, pos, 1, new Random().nextFloat() + 0.5f);
        player.displayClientMessage(Component.translatable("create_bb.tooltips.rotation"), true);

        return InteractionResult.CONSUME;
    }

    private static boolean isDirectionPositive(Direction direction) {
        return direction == Direction.NORTH || direction == Direction.WEST || direction == Direction.UP;
    }
}

