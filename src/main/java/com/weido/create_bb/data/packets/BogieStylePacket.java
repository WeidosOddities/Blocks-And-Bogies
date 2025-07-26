package com.weido.create_bb.data.packets;

import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeySizes.BogeySize;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.createmod.catnip.data.Pair;
import net.createmod.catnip.net.base.BasePacketPayload;
import net.createmod.catnip.net.base.ServerboundPacketPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.weido.create_bb.registry.BogiePackets;
import com.weido.create_bb.data.menu.Entry.StyleMenuHandler;

public abstract class BogieStylePacket implements CustomPacketPayload {
    private final BasePacketPayload.PacketTypeProvider typeProvider;

    protected BogieStylePacket(BasePacketPayload.PacketTypeProvider typeProvider) {
        this.typeProvider = typeProvider;
    }

    public BasePacketPayload.PacketTypeProvider getTypeProvider() {
        return typeProvider;
    }

    public static class Serverbound extends BogieStylePacket implements ServerboundPacketPayload {
        final BogeyStyle style;
        @Nullable
        final BogeySize size;
        final BlockPos pos;

        public Serverbound(@NotNull BogeyStyle style, @Nullable BogeySize size, BlockPos pos) {
            super(BogiePackets.C_BOGIE_STYLE);
            this.style = style;
            this.size = size;
            this.pos = pos;
        }

        public static final StreamCodec<FriendlyByteBuf, Serverbound> STREAM_CODEC = StreamCodec.ofMember(
            (Serverbound packet, FriendlyByteBuf buf) -> {
                buf.writeResourceLocation(packet.style.id);
                buf.writeBoolean(packet.size != null);
                if (packet.size != null) {
                    buf.writeResourceLocation(packet.size.id());
                }
                buf.writeBlockPos(packet.pos);
            },
            (FriendlyByteBuf buf) -> {
                ResourceLocation loc = buf.readResourceLocation();
                BogeyStyle style = AllBogeyStyles.BOGEY_STYLES.getOrDefault(loc, AllBogeyStyles.STANDARD);
                BogeySize size = null;
                if (buf.readBoolean()) {
                    ResourceLocation sizeLoc = buf.readResourceLocation();
                    size = BogeySizes.all().get(sizeLoc);
                }
                BlockPos pos = buf.readBlockPos();
                return new Serverbound(style, size, pos);
            }
        );

        public void handle(ServerPlayer player) {
            Level level = player.level();
            if (level.getBlockEntity(pos) instanceof AbstractBogeyBlockEntity) {
                Block newBlock = style.getBlockForSize(size);
                BlockState newState = newBlock.defaultBlockState()
                    .setValue(BlockStateProperties.HORIZONTAL_AXIS,
                        level.getBlockState(pos).getValue(BlockStateProperties.HORIZONTAL_AXIS));

                level.setBlock(pos, newState, 3);
                if (level.getBlockEntity(pos) instanceof AbstractBogeyBlockEntity newBe) {
                    newBe.setBogeyStyle(style);
                }
            }

            if (size != null) {
                StyleMenuHandler.addStyle(player.getUUID(), Pair.of(style, size));
            }
        }
    }
}
