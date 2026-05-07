package com.pricing;

public enum DiscountCode {
    SAVE10(0.10),
    SAVE20(0.20),
    SAVE30(0.30),
    NONE(0.0);

    private final double rate;

    DiscountCode(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public static DiscountCode fromString(String code) {
        if (code == null) return NONE;
        try {
            return valueOf(code.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }
}
