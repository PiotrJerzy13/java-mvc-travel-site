package com.piotrwojnarowski.domain;

public enum Category {
    RELAXATION("Relaxation"),
    SHOPPING("Shopping"),
    CULTURE("Culture"),
    ARCHITECTURE("Architecture"),
    HISTORY("History"),
    NATURE("Nature");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
