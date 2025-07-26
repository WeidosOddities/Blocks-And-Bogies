package com.weido.create_bb.data.menu.Input;

import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.foundation.gui.widget.ScrollInput;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import java.util.Set;

public class SizeScrollInput extends ScrollInput {
    private final MutableComponent scrollToSelect = Component.translatable("create_bb.menu.scroll_selection");
    private final MutableComponent noOptionAvailable = Component.translatable("create_bb.menu.no_options_available");

    private BogeySizes.BogeySize[] sizes = { BogeySizes.SMALL, BogeySizes.LARGE };
    private boolean isDriver = false;

    public SizeScrollInput(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn);
        withRange(0, sizes.length)
            .titled(Component.translatable("create_bb.menu.size"))
            .setState(0)
            .inverted();
        updateTooltip();
    }

    public void setDriver(boolean isDriver) {
        this.isDriver = isDriver;
        updateTooltip();
    }

    public BogeySizes.BogeySize[] getSizes() { return sizes; }

    public void updateOptions(Set<BogeySizes.BogeySize> validSizes) {
        if (validSizes.isEmpty()) {
            validSizes.add(BogeySizes.SMALL);
        }
        this.sizes = validSizes.toArray(new BogeySizes.BogeySize[0]);
        withRange(0, sizes.length);
        this.state = Math.min(state, sizes.length);
        updateTooltip();
    }

    @Override
    protected void updateTooltip() {
        toolTip.clear();
        if (title != null) {
            toolTip.add(title.copy().withStyle(s -> s.withColor(HEADER_RGB.getRGB())));
        }
        for (int i = 0; i < sizes.length; i++) {
            MutableComponent sizeText = Component.empty()
                .append(i == state ? "-> " : "> ")
                .append(Component.translatable(sizes[i] == BogeySizes.LARGE ?
                    (isDriver ? "create_bb.menu.size.extra_large" : "create_bb.menu.size.large") :
                    (isDriver ? "create_bb.menu.size.large" : "create_bb.menu.size.small")));
            toolTip.add(sizeText.withStyle(i == state ? ChatFormatting.WHITE : ChatFormatting.GRAY));
        }
        if (sizes.length == 1) {
            toolTip.add(noOptionAvailable.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        } else {
            toolTip.add(scrollToSelect.plainCopy()
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }

    public BogeySizes.BogeySize getCurrentSize() {
        return sizes[getState()];
    }
}