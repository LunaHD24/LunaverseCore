package dev.lunaa.lunaversecore.api.attribute;

import dev.lunaa.lunaversecore.api.common.ValueFormat;
import dev.lunaa.lunaversecore.api.common.ValueModifier;

public record StatValue(float value, ValueModifier modifier, ValueFormat format) {

    public boolean isPositive() {
        if (modifier == ValueModifier.MULTIPLY) {
            return value >= 1.0f;
        }
        return modifier == ValueModifier.ADD;
    }

}
