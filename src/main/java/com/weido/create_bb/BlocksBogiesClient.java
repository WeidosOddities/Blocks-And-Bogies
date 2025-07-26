package com.weido.create_bb;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.createmod.catnip.render.SuperByteBufferCache;
import net.createmod.catnip.render.CachedBuffers;

import com.weido.create_bb.registry.BogiePartials;
@Mod(value = BlocksBogies.MOD_ID, dist = Dist.CLIENT)
public class BlocksBogiesClient {
    public BlocksBogiesClient(net.neoforged.bus.api.IEventBus modEventBus) {
        onCtorClient(modEventBus);
    }
    public static void onCtorClient(net.neoforged.bus.api.IEventBus modEventBus) {
        modEventBus.addListener(BlocksBogiesClient::clientInit);
    }
    public static void clientInit(final FMLClientSetupEvent event) {
        SuperByteBufferCache.getInstance().registerCompartment(CachedBuffers.PARTIAL);
        SuperByteBufferCache.getInstance().registerCompartment(CachedBuffers.DIRECTIONAL_PARTIAL);
        BogiePartials.init();
    }
}
