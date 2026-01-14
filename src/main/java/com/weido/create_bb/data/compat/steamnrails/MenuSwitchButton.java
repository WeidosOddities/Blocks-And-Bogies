package com.weido.create_bb.data.compat.steamnrails;

import com.railwayteam.railways.content.bogey_menu.BogeyMenuScreen;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.widget.IconButton;
import net.createmod.catnip.gui.ScreenOpener;
import net.createmod.catnip.gui.widget.AbstractSimiWidget;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class MenuSwitchButton {
    private static BlockPos targetPos;

    public static BlockPos getTargetPos() {
        return targetPos;
    }

    public static void setTargetPos(BlockPos pos) {
        targetPos = pos;
    }

    public static IconButton create(int x, int y, BlockPos targetPos, Runnable onMenuSwitch) {
        IconButton menuSwitchButton = new IconButton(x, y, AllIcons.I_DICE)
                .withCallback(() -> {
                    BogeyMenuScreen screen = new BogeyMenuScreen();
                    setTargetPos(targetPos);
                    ScreenOpener.open(screen);
                    onMenuSwitch.run();
                });
        menuSwitchButton.setToolTip(Component.translatable("create_bb.tooltips.switch_to_bogey_menu").withStyle(s -> s.withColor(AbstractSimiWidget.HEADER_RGB.getRGB())));
        return menuSwitchButton;
    }
}