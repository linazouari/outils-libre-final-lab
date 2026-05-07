package com.pricing;

public enum CustomerType {
    REGULAR(0.20),
    VIP(0.10);

    private final double taxRate;

    CustomerType(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
