package com.weido.create_bb.data.menu.Entry;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class StyleEntryManager {
    private static final @NotNull List<StyleEntry> styleEntryList = new ArrayList<>();

    public static @NotNull List<StyleEntry> getBogeyEntryList() {
        return styleEntryList;
    }

    public void addToBogeyEntryList(StyleEntry entry) {
        styleEntryList.add(entry);
    }
}
