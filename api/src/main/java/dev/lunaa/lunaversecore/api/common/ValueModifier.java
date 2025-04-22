package dev.lunaa.lunaversecore.api.common;

public enum ValueModifier {

    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x');


    private final char character;

    ValueModifier(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

}
