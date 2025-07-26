package com.weido.create_bb.data.menu.Input;

import com.simibubi.create.foundation.gui.widget.ScrollInput;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import com.weido.create_bb.data.menu.Entry.StyleEntry;

import java.util.*;

public class ValveGearScrollInput extends ScrollInput {
    private final MutableComponent scrollToSelect = Component.translatable("create_bb.menu.scroll_selection");
    private final MutableComponent noOptionAvailable = Component.translatable("create_bb.menu.no_options_available");
    public StyleEntry.ValveGear[] valveGears;

    public ValveGearScrollInput(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn);
        this.valveGears = StyleEntry.ValveGear.values();
        withRange(0, valveGears.length)
            .titled(Component.translatable("create_bb.menu.valve_gear"))
            .format(index -> index >= valveGears.length ? Component.empty() : valveGears[index].getDisplayText())
            .inverted()
            .setState(0);
        updateTooltip();
    }

    public void updateOptions(Set<StyleEntry.ValveGear> validVariants) {
        LinkedList<StyleEntry.ValveGear> sortedList = new LinkedList<>(validVariants);

        List<StyleEntry.ValveGear> priorityOrder = List.of(
            StyleEntry.ValveGear.NONE,
            StyleEntry.ValveGear.WALSCHAERTS,
            StyleEntry.ValveGear.SCOTCH_YOKE,
            StyleEntry.ValveGear.ROTARY_POPPET
        );

        sortedList.sort(Comparator.comparingInt(priorityOrder::indexOf));

        this.valveGears = sortedList.toArray(new StyleEntry.ValveGear[0]);
        withRange(0, valveGears.length);
        if (state >= valveGears.length) {
            setState(0);
        }
        this.format(index -> index >= valveGears.length ? Component.empty() : valveGears[index].getDisplayText());
        updateTooltip();
    }

    @Override
    protected void updateTooltip() {
        toolTip.clear();
        if (title != null) {
            toolTip.add(title.copy().withStyle(s -> s.withColor(HEADER_RGB.getRGB())));
        }
        for (int i = 0; i < valveGears.length; i++) {
            MutableComponent valvegear = Component.empty()
                .append(i == state ? "-> " : "> ")
                .append(valveGears[i].getDisplayText());
            toolTip.add(valvegear.withStyle(i == state ? ChatFormatting.WHITE : ChatFormatting.GRAY));
        }
        if (valveGears.length == 1) {
            toolTip.add(noOptionAvailable.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        } else {
            toolTip.add(scrollToSelect.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }
}