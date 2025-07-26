package com.weido.create_bb.registry;

import com.weido.create_bb.BlocksBogies;
import com.weido.create_bb.data.packets.BogieMenuPacket;
import com.weido.create_bb.data.packets.BogieStylePacket;
import net.createmod.catnip.net.base.BasePacketPayload;
import net.createmod.catnip.net.base.CatnipPacketRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import java.util.Locale;

public enum BogiePackets implements BasePacketPayload.PacketTypeProvider {

    S_BOGIE_MENU(BogieMenuPacket.Clientbound.class, BogieMenuPacket.Clientbound.STREAM_CODEC),

    C_BOGIE_MENU(BogieMenuPacket.Serverbound.class, BogieMenuPacket.Serverbound.STREAM_CODEC),
    C_BOGIE_STYLE(BogieStylePacket.Serverbound.class, BogieStylePacket.Serverbound.STREAM_CODEC),
    ;
    private final CatnipPacketRegistry.PacketType<?> type;

    <T extends BasePacketPayload> BogiePackets(Class<T> clazz, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        String name = this.name().toLowerCase(Locale.ROOT);
        this.type = new CatnipPacketRegistry.PacketType<>(
            new CustomPacketPayload.Type<>(BlocksBogies.asResource(name)),
            clazz, codec
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends CustomPacketPayload> CustomPacketPayload.Type<T> getType() {
        return (CustomPacketPayload.Type<T>) this.type.type();
    }

    public static void register() {
        CatnipPacketRegistry packetRegistry = new CatnipPacketRegistry(BlocksBogies.MOD_ID, 1);
        for (BogiePackets packet : BogiePackets.values()) {
            packetRegistry.registerPacket(packet.type);
        }
        packetRegistry.registerAllPackets();
    }
}
