package com.weido.create_bb.data.menu.Input;

import com.simibubi.create.foundation.gui.widget.ScrollInput;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class AxleCountScrollInput extends ScrollInput {
    private final MutableComponent scrollToSelect = Component.translatable("create_bb.menu.scroll_selection");
    private final MutableComponent noOptionAvailable = Component.translatable("create_bb.menu.no_options_available");
    private int minAxles;
    private int maxAxles;

    public AxleCountScrollInput(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn);
        this.minAxles = 1;
        this.maxAxles = 6;
        withRange(minAxles, maxAxles + 1)
            .titled(Component.translatable("create_bb.menu.axle_count"))
            .format(index -> index > maxAxles ? Component.empty() : Component.literal(String.valueOf(index)))
            .inverted()
            .setState(2);
        updateTooltip();
    }

    public void updateOptions(int min, int max) {
        this.minAxles = min;
        this.maxAxles = max;
        withRange(minAxles, maxAxles + 1);
        if (state > maxAxles) {
            setState(maxAxles);
        } else if (state < minAxles) {
            setState(minAxles);
        }
        this.format(index -> index > maxAxles ? Component.empty() : Component.literal(String.valueOf(index)));
        updateTooltip();
    }

    public int getMinAxles() {
        return minAxles;
    }

    public int getMaxAxles() {
        return maxAxles;
    }

    @Override
    protected void updateTooltip() {
        toolTip.clear();
        if (title != null) {
            toolTip.add(title.copy().withStyle(s -> s.withColor(HEADER_RGB.getRGB())));
        }
        if (minAxles == maxAxles) {
            toolTip.add(noOptionAvailable.plainCopy()
                    .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        } else {
            for (int i = minAxles; i <= maxAxles; i++) {
                MutableComponent axleCount = Component.empty()
                    .append(i == state ? "-> " : "> ")
                    .append(String.valueOf(i));
                toolTip.add(axleCount.withStyle(i == state ? ChatFormatting.WHITE : ChatFormatting.GRAY));
            }
            toolTip.add(scrollToSelect.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }
}