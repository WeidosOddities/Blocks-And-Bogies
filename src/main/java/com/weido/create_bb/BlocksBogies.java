package com.weido.create_bb;

import com.weido.create_bb.registry.BogieBlockEntities;
import com.weido.create_bb.registry.BogieBlocks;
import com.weido.create_bb.registry.BogiePackets;
import com.weido.create_bb.registry.BogieStyles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraft.resources.ResourceLocation;
import com.simibubi.create.foundation.data.CreateRegistrate;

@Mod(BlocksBogies.MOD_ID)
public class BlocksBogies {
    public static final String MOD_ID = "create_bb";
    public static final String MOD_NAME = "Create: Blocks & Bogies";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public BlocksBogies(IEventBus eventBus) {
        onCtor(eventBus);
    }
    public static void onCtor(IEventBus modEventBus) {
        REGISTRATE.registerEventListeners(modEventBus);

        BogieStyles.register();
        BogieBlocks.register();
        BogiePackets.register();
        BogieBlockEntities.register();

        modEventBus.addListener(BlocksBogies::init);
    }

    public static void init(final FMLCommonSetupEvent event) {
        LOGGER.info("{} initializing...", MOD_NAME);
    }
    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}