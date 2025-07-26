package com.weido.create_bb.data.menu.Entry;

import org.jetbrains.annotations.Nullable;
import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeySizes.BogeySize;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.createmod.catnip.data.Pair;
import java.util.*;

public class StyleMenuHandler {

    @Nullable private static UUID currentPlayer = null;
    private static final HashMap<UUID, Pair<BogeyStyle, @Nullable BogeySize>> selectedStyles = new HashMap<>();

    public static @Nullable UUID getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(@Nullable UUID uuid) {
        currentPlayer = uuid;
    }

    public static void addStyle(UUID uuid, Pair<BogeyStyle, @Nullable BogeySize> pair) {
        selectedStyles.put(uuid, pair);
    }

    public static Pair<BogeyStyle, @Nullable BogeySize> getStyle(UUID uuid) {
        if (selectedStyles.containsKey(uuid))
            return selectedStyles.get(uuid);
        return Pair.of(AllBogeyStyles.STANDARD, BogeySizes.SMALL);
    }
}
