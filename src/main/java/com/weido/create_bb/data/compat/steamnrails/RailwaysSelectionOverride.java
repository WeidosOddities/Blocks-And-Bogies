package com.weido.create_bb.data.compat.steamnrails;

import com.railwayteam.railways.content.bogey_menu.handler.BogeyMenuHandlerServer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.createmod.catnip.data.Pair;

import java.util.UUID;

public class RailwaysSelectionOverride {
    public static void addStyle(UUID playerId, Pair<?, ?> styleSizePair) {
        Pair<BogeyStyle, BogeySizes.BogeySize> typedPair = (Pair<BogeyStyle, BogeySizes.BogeySize>) styleSizePair;
        BogeyMenuHandlerServer.addStyle(playerId, typedPair);
    }
}
