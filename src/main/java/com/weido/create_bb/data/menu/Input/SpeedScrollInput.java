package com.weido.create_bb.data.menu.Input;

import com.simibubi.create.foundation.gui.widget.ScrollInput;
import net.minecraft.network.chat.Component;

public class SpeedScrollInput extends ScrollInput {
    public SpeedScrollInput(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn);
        int minSpeed = -100;
        int maxSpeed = 100;
        withRange(minSpeed, maxSpeed + 1)
            .titled(Component.translatable("create_bb.menu.speed"))
            .format(speed -> Component.literal(speed > maxSpeed ? "" : String.valueOf(speed)))
            .setState(0);
    }
}