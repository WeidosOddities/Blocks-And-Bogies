package com.weido.create_bb.data.compat.steamnrails;

import com.railwayteam.railways.content.bogey_menu.handler.BogeyMenuHandlerServer;
import com.simibubi.create.content.trains.bogey.BogeySizes.BogeySize;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.createmod.catnip.data.Pair;
import java.util.UUID;

public class MixinOverlapFix {
    @SuppressWarnings("unchecked")
    public static void addStyle(UUID playerId, Pair<?, ?> styleSizePair) {
        Pair<BogeyStyle, BogeySize> typedPair = (Pair<BogeyStyle, BogeySize>) styleSizePair;
        BogeyMenuHandlerServer.addStyle(playerId, typedPair);
    }
}