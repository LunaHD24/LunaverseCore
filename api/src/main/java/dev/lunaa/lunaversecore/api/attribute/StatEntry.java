package dev.lunaa.lunaversecore.api.attribute;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.translation.GlobalTranslator;

import java.util.Locale;

public record StatEntry(StatType type, StatValue value) {

    public String serialize() {
        return String.format("%s;%s;%s;%s", type.getKey(), value.value(), value().modifier().getSymbol(), value().format().name());
    }

    public Component readableFormat(Locale locale) {
        return GlobalTranslator.render(Component.translatable(type.getTranslationKey()), locale).color(NamedTextColor.GRAY)
                .append(Component.text(": ").color(NamedTextColor.GRAY))
                .append(Component.text(value.ofString()).color(value.isPositive() ? NamedTextColor.GREEN : NamedTextColor.RED))
                .append(type.getCharacterComponent())
                .decoration(TextDecoration.ITALIC, false);
    }

}
