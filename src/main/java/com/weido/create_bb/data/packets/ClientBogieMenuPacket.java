package com.weido.create_bb.data.packets;

import com.simibubi.create.foundation.networking.SimplePacketBase;
import com.weido.create_bb.registry.BogiePackets;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public class ClientBogieMenuPacket extends SimplePacketBase {
    private final BlockPos pos;

    public ClientBogieMenuPacket(BlockPos pos) {
        this.pos = pos;
    }

    public ClientBogieMenuPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public boolean handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
             ServerPlayer player = context.getSender();
             BogiePackets.getChannel().send(PacketDistributor.PLAYER.with(() -> player), new ServerBogieMenuPacket(pos));
        });
        return true;
    }
}