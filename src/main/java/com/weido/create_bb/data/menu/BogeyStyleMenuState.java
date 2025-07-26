package com.weido.create_bb.data.menu;

import com.weido.create_bb.data.menu.Entry.StyleEntry;

public class BogeyStyleMenuState {
    private static StyleEntry.Type lastType = StyleEntry.Type.TRUCK;
    private static int lastVariant = 0;
    private static int lastValvegear = 0;
    private static int lastAxleCount = 2;
    private static int lastSize = 0;
    private static int lastBogieLength = 0;

    public static void saveState(StyleEntry.Type type, int variant, int valvegear, int axleCount, int size, int bogeyLength) {
        lastType = type;
        lastVariant = variant;
        lastValvegear = valvegear;
        lastAxleCount = axleCount;
        lastSize = size;
        lastBogieLength = bogeyLength;
    }

    public static StyleEntry.Type getLastType() { return lastType; }
    public static int getLastVariant() { return lastVariant; }
    public static int getLastValvegear() { return lastValvegear; }
    public static int getLastAxleCount() { return lastAxleCount; }
    public static int getLastSize() { return lastSize; }
    public static int getLastBogieLength() { return lastBogieLength; }
}