package com.weido.create_bb;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.createmod.catnip.render.SuperByteBufferCache;
import net.createmod.catnip.render.CachedBuffers;

import com.weido.create_bb.registry.BogiePartials;

public class BlocksBogiesClient {
    public BlocksBogiesClient(IEventBus modEventBus) {
        onCtorClient(modEventBus);
    }
    public static void onCtorClient(IEventBus modEventBus) {
        modEventBus.addListener(BlocksBogiesClient::clientInit);
    }
    public static void clientInit(final FMLCommonSetupEvent event) {
        SuperByteBufferCache.getInstance().registerCompartment(CachedBuffers.PARTIAL);
        SuperByteBufferCache.getInstance().registerCompartment(CachedBuffers.DIRECTIONAL_PARTIAL);
        BogiePartials.init();
    }
}
