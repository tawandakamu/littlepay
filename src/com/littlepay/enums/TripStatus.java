package com.littlepay.enums;

public enum TripStatus {
    INCOMPLETE("INCOMPLETE"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    public final String title;

    private TripStatus(String title) {
        this.title = title;
    }
}
