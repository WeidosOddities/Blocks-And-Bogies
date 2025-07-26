package com.weido.create_bb.data.menu.Input;

import com.simibubi.create.foundation.gui.widget.ScrollInput;
import com.weido.create_bb.data.menu.Entry.StyleEntry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LengthScrollInput extends ScrollInput {
    private final MutableComponent scrollToSelect = Component.translatable("create_bb.menu.scroll_selection");
    private final MutableComponent noOptionAvailable = Component.translatable("create_bb.menu.no_options_available");
    public StyleEntry.Length[] bogieLength;

    public LengthScrollInput(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn);
        this.bogieLength = StyleEntry.Length.values();
        withRange(0, bogieLength.length)
            .titled(Component.translatable("create_bb.menu.length"))
            .format(index -> index >= bogieLength.length ? Component.empty() : bogieLength[index].getDisplayText())
            .inverted()
            .setState(0);
        updateTooltip();
    }

    public void updateOptions(Set<StyleEntry.Length> validVariants) {
        LinkedList<StyleEntry.Length> sortedList = new LinkedList<>(validVariants);

        List<StyleEntry.Length> priorityOrder = List.of(
                StyleEntry.Length.NORMAL,
                StyleEntry.Length.EXTENDED,
                StyleEntry.Length.OFFSET
        );

        sortedList.sort(Comparator.comparingInt(priorityOrder::indexOf));

        this.bogieLength = sortedList.toArray(new StyleEntry.Length[0]);
        withRange(0, bogieLength.length);
        if (state >= bogieLength.length) {
            setState(0);
        }
        this.format(index -> index >= bogieLength.length ? Component.empty() : bogieLength[index].getDisplayText());
        updateTooltip();
    }

    @Override
    protected void updateTooltip() {
        toolTip.clear();
        if (title != null) {
            toolTip.add(title.copy().withStyle(s -> s.withColor(HEADER_RGB.getRGB())));
        }
        for (int i = 0; i < bogieLength.length; i++) {
            MutableComponent valvegear = Component.empty()
                .append(i == state ? "-> " : "> ")
                .append(bogieLength[i].getDisplayText());
            toolTip.add(valvegear.withStyle(i == state ? ChatFormatting.WHITE : ChatFormatting.GRAY));
        }
        if (bogieLength.length == 1) {
            toolTip.add(noOptionAvailable.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        } else {
            toolTip.add(scrollToSelect.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }
}