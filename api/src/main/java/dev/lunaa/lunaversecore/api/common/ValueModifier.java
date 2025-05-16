package dev.lunaa.lunaversecore.api.common;

public enum ValueModifier {

    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x');


    private final char symbol;

    ValueModifier(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public static ValueModifier fromSymbol(char symbol) {
        for (ValueModifier modifier : values()) {
            if (modifier.getSymbol() == symbol) {
                return modifier;
            }
        }
        return null;
    }

}
