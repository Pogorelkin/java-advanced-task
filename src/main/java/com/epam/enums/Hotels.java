package com.epam.enums;

public enum Hotels {
    ALPHA("Alpha"),
    BETTA("Betta"),
    GAMMA("Gamma"),
    TETRA("Tetra"),
    QUADRA("Quadra"),
    PENTA("Penta"),
    SOMEHOTel("Somehotel)"),
    FIVSTAHR("Five_stars");

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
