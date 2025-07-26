package com.weido.create_bb.data.packets;

import com.weido.create_bb.data.menu.BogieStyleSelectionScreen;
import com.weido.create_bb.registry.BogiePackets;
import net.createmod.catnip.gui.ScreenOpener;
import net.createmod.catnip.net.base.BasePacketPayload;
import net.createmod.catnip.net.base.ClientboundPacketPayload;
import net.createmod.catnip.net.base.ServerboundPacketPayload;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;

public abstract class BogieMenuPacket implements CustomPacketPayload {
    private final BasePacketPayload.PacketTypeProvider typeProvider;

    protected BogieMenuPacket(BasePacketPayload.PacketTypeProvider typeProvider) {
        this.typeProvider = typeProvider;
    }

    public BasePacketPayload.PacketTypeProvider getTypeProvider() {
        return typeProvider;
    }

    public static class Clientbound extends BogieMenuPacket implements ClientboundPacketPayload {
        private final BlockPos pos;

        public static final StreamCodec<FriendlyByteBuf, Clientbound> STREAM_CODEC = StreamCodec.ofMember(
                (packet, buf) -> buf.writeBlockPos(packet.pos),
                buf -> new Clientbound(buf.readBlockPos())
        );

        public Clientbound(BlockPos pos) {
            super(BogiePackets.S_BOGIE_MENU);
            this.pos = pos;
        }

        @Override
        @OnlyIn(Dist.CLIENT)
        public void handle(LocalPlayer player) {
            ScreenOpener.open(new BogieStyleSelectionScreen(pos));
        }
    }

    public static class Serverbound extends BogieMenuPacket implements ServerboundPacketPayload {
        private final BlockPos pos;

        public static final StreamCodec<FriendlyByteBuf, Serverbound> STREAM_CODEC = StreamCodec.ofMember(
                (packet, buf) -> buf.writeBlockPos(packet.pos),
                buf -> new Serverbound(buf.readBlockPos())
        );

        public Serverbound(BlockPos pos) {
            super(BogiePackets.C_BOGIE_MENU);
            this.pos = pos;
        }

        public void handle(ServerPlayer player) {
            PacketDistributor.sendToPlayer(player, new BogieMenuPacket.Clientbound(pos));
        }
    }
}