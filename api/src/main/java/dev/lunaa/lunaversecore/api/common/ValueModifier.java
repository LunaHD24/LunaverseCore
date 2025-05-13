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

}
