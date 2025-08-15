package com.weido.create_bb;

import com.weido.create_bb.registry.BogieBlockEntities;
import com.weido.create_bb.registry.BogieBlocks;
import com.weido.create_bb.registry.BogiePackets;
import com.weido.create_bb.registry.BogieStyles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.resources.ResourceLocation;
import com.simibubi.create.foundation.data.CreateRegistrate;

@Mod(BlocksBogies.MOD_ID)
public class BlocksBogies {
    public static final String MOD_ID = "create_bb";
    public static final String MOD_NAME = "Create: Blocks & Bogies";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static CreateRegistrate REGISTRATE;

    public BlocksBogies() {
        REGISTRATE = CreateRegistrate.create(MOD_ID);
        onCtor();
    }

    public static void onCtor() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get()
                .getModEventBus();

        REGISTRATE.registerEventListeners(modEventBus);

        BogieStyles.register();
        BogieBlocks.register();
        BogiePackets.registerPackets();
        BogieBlockEntities.register();

        modEventBus.addListener(BlocksBogies::init);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> BlocksBogiesClient.onCtorClient(modEventBus));
    }

    public static void init(final FMLCommonSetupEvent event) {
        LOGGER.info("{} initializing...", MOD_NAME);
    }
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}