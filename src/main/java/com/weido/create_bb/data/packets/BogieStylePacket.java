package com.weido.create_bb.data.packets;

import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeySizes.BogeySize;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import com.weido.create_bb.data.compat.steamnrails.MixinOverlapFix;
import net.createmod.catnip.data.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.weido.create_bb.data.menu.Entry.StyleMenuHandler;

public class BogieStylePacket extends SimplePacketBase {
    private final BogeyStyle style;
    @Nullable
    private final BogeySize size;
    @Nullable
    private final BlockPos pos;

    public BogieStylePacket(@NotNull BogeyStyle style, @Nullable BogeySize size, @Nullable BlockPos pos) {
        this.style = style;
        this.size = size;
        this.pos = pos;
    }

    public BogieStylePacket(FriendlyByteBuf buf) {
        ResourceLocation styleLoc = buf.readResourceLocation();
        this.style = AllBogeyStyles.BOGEY_STYLES.getOrDefault(styleLoc, AllBogeyStyles.STANDARD);
        if (buf.readBoolean()) {
            ResourceLocation sizeLoc = buf.readResourceLocation();
            this.size = BogeySizes.all().get(sizeLoc);
        } else {
            this.size = null;
        }
        if (buf.readBoolean()) {
            this.pos = buf.readBlockPos();
        } else {
            this.pos = null;
        }
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeResourceLocation(style.id);
        buf.writeBoolean(size != null);
        if (size != null) {
            buf.writeResourceLocation(size.id());
        }
        buf.writeBoolean(pos != null);
        if (pos != null) {
            buf.writeBlockPos(pos);
        }
    }

    @Override
    public boolean handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            Level level = player.level();
            if (pos != null && level.getBlockEntity(pos) instanceof AbstractBogeyBlockEntity) {
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
                if (ModList.get().isLoaded("railways")) {
                    MixinOverlapFix.addStyle(player.getUUID(), Pair.of(style, size));
                }
                else {
                    StyleMenuHandler.addStyle(player.getUUID(), Pair.of(style, size));
                }
            }
        });
        return true;
    }
}