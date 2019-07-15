package com.epam.enums;

public enum Hotels {
    ALPHA("Alpha"),
    BETTA("Betta"),
    GAMMA("Gamma"),
    TETRA("Tetra"),
    QUADRA("Quadra"),
    PENTA("Penta");

    private String string;

    Hotels(String string) {
        this.string = string;
    }

    public String getHotel() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }
}
