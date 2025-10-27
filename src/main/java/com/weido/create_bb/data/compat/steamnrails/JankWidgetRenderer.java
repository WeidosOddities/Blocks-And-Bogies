package com.weido.create_bb.data.compat.steamnrails;

import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;

import java.lang.reflect.Method;

public class JankWidgetRenderer {
    public static <T extends GuiEventListener & Renderable & NarratableEntry> void addRenderableWidgetReflect(Screen screen, T widget) {
        try {
            Method method;
            try {
                method = Screen.class.getDeclaredMethod("addRenderableWidget", GuiEventListener.class);
            } catch (NoSuchMethodException e) {
                method = Screen.class.getDeclaredMethod("m_142416_", GuiEventListener.class);
            }
            method.setAccessible(true);
            method.invoke(screen, widget);
        } catch (Exception ignored) {
        }
    }
}
