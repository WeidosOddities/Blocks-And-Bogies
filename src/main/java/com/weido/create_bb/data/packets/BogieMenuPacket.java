package com.weido.create_bb.data.packets;

import com.simibubi.create.foundation.networking.SimplePacketBase;
import com.weido.create_bb.data.menu.BogieStyleSelectionScreen;
import net.createmod.catnip.gui.ScreenOpener;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

public class BogieMenuPacket extends SimplePacketBase {
    private final BlockPos pos;

    public BogieMenuPacket(BlockPos pos) {
        this.pos = pos;
    }

    public BogieMenuPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public boolean handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isClient()) {
                handleClient(pos);
            }
        });
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    private static void handleClient(BlockPos pos) {
        ScreenOpener.open(new BogieStyleSelectionScreen(pos));
    }
}