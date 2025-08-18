package com.weido.create_bb.data.accessors;

import net.createmod.catnip.gui.AbstractSimiScreen;

import java.lang.reflect.Field;

public class SimiScreenAccessor {
    public static int getGuiLeft(AbstractSimiScreen screen) {
        try {
            Field field = AbstractSimiScreen.class.getDeclaredField("guiLeft");
            field.setAccessible(true);
            return field.getInt(screen);
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static int getGuiTop(AbstractSimiScreen screen) {
        try {
            Field field = AbstractSimiScreen.class.getDeclaredField("guiTop");
            field.setAccessible(true);
            return field.getInt(screen);
        } catch (Exception ignored) {
            return 0;
        }
    }
}