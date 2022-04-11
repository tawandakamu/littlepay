package com.littlepay.enums;

public enum TapType {
    ON("ON"),
    OFF("OFF");

    public final String title;

    private TapType(String title) {
        this.title = title;
    }
}
