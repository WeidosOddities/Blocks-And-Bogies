package com.weido.create_bb;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import com.weido.create_bb.registry.BogiePartials;

public class BlocksBogiesClient {
    public static void onCtorClient(IEventBus modEventBus) {
        modEventBus.addListener(BlocksBogiesClient::clientInit);
    }

    public static void clientInit(final FMLCommonSetupEvent event) {
        BogiePartials.init();
    }
}
