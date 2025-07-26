package com.weido.create_bb.data.menu.Entry;

import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record StyleEntry(@NotNull BogeyStyle bogeyStyle, Variant variant, ValveGear valveGear, Type type, int axleCount, Length length) {
    private static final Map<StyleKey, StyleEntry> STYLES = new ConcurrentHashMap<>();

    public record StyleKey(BogeyStyle bogeyStyle, Variant variant, ValveGear valveGear, Type type, int axleCount, Length length) {}

    public enum Type {
        DRIVER("create_bb.type.driver"),
        TRUCK("create_bb.type.truck");

        private final String translationKey;

        Type(String translationKey) {
            this.translationKey = translationKey;
        }

        public Component getDisplayText() {
            return Component.translatable(translationKey);
        }
    }

    public enum ValveGear {
        NONE("create_bb.valve_gear.none"),
        WALSCHAERTS("create_bb.valve_gear.walschaerts"),
        ROTARY_POPPET("create_bb.valve_gear.rotary_poppet"),
        SCOTCH_YOKE("create_bb.valve_gear.scotch_yoke");

        private final String translationKey;

        ValveGear(String translationKey) {
            this.translationKey = translationKey;
        }

        public Component getDisplayText() {
            return Component.translatable(translationKey);
        }
    }

    public enum Variant {
        STANDARD("create_bb.variant.standard"),
        LONG("create_bb.variant.long"),
        SHORT("create_bb.variant.short"),
        PISTONLESS("create_bb.variant.pistonless"),
        TRAILING("create_bb.variant.trailing");
        private final String translationKey;

        Variant(String translationKey) {
            this.translationKey = translationKey;
        }

        public Component getDisplayText() {
            return Component.translatable(translationKey);
        }
    }

    public enum Length {
        NORMAL("create_bb.length.standard"),
        EXTENDED("create_bb.length.extended"),
        OFFSET("create_bb.length.offset");
        private final String translationKey;

        Length(String translationKey) {
            this.translationKey = translationKey;
        }

        public Component getDisplayText() {
            return Component.translatable(translationKey);
        }
    }

    public static StyleEntry getOrCreate(@NotNull BogeyStyle bogeyStyle, Variant variant, ValveGear valveGear, Type type, int axleCount, Length length) {
        StyleKey key = new StyleKey(bogeyStyle, variant, valveGear, type, axleCount, length);
        return STYLES.computeIfAbsent(key, k ->
            new StyleEntry(k.bogeyStyle(), k.variant(), k.valveGear(), k.type(), k.axleCount(), k.length()));
    }
}
