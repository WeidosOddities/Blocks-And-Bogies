package com.weido.create_bb.data.menu.Input;

import com.simibubi.create.foundation.gui.widget.ScrollInput;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import com.weido.create_bb.data.menu.Entry.StyleEntry;

import java.util.*;

public class VariantScrollInput extends ScrollInput {
    private final MutableComponent scrollToSelect = Component.translatable("create_bb.menu.scroll_selection");
    private final MutableComponent noOptionAvailable = Component.translatable("create_bb.menu.no_options_available");
    public StyleEntry.Variant[] variants;

    public VariantScrollInput(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn);
        this.variants = StyleEntry.Variant.values();
        withRange(0, variants.length)
            .titled(Component.translatable("create_bb.menu.variant"))
            .format(index -> index >= variants.length ? Component.empty() : variants[index].getDisplayText())
            .inverted()
            .setState(0);
        updateTooltip();
    }

    public void updateOptions(Set<StyleEntry.Variant> validVariants) {
        LinkedList<StyleEntry.Variant> sortedList = new LinkedList<>(validVariants);

        List<StyleEntry.Variant> priorityOrder = List.of(
            StyleEntry.Variant.LONG,
            StyleEntry.Variant.SHORT,
            StyleEntry.Variant.PISTONLESS,
            StyleEntry.Variant.STANDARD,
            StyleEntry.Variant.TRAILING
        );

        sortedList.sort(Comparator.comparingInt(priorityOrder::indexOf));

        this.variants = sortedList.toArray(new StyleEntry.Variant[0]);
        withRange(0, variants.length);
        if (state >= variants.length) {
            setState(0);
        }
        this.format(index -> index >= variants.length ? Component.empty() : variants[index].getDisplayText());
        updateTooltip();
    }

    @Override
    protected void updateTooltip() {
        toolTip.clear();
        if (title != null) {
            toolTip.add(title.copy().withStyle(s -> s.withColor(HEADER_RGB.getRGB())));
        }
        for (int i = 0; i < variants.length; i++) {
            MutableComponent variant = Component.empty()
                .append(i == state ? "-> " : "> ")
                .append(variants[i].getDisplayText());
            toolTip.add(variant.withStyle(i == state ? ChatFormatting.WHITE : ChatFormatting.GRAY));
        }
        if (variants.length == 1) {
            toolTip.add(noOptionAvailable.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        } else {
            toolTip.add(scrollToSelect.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }
}