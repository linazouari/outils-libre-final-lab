package com.pricing;

public class OrderItem {
    private final double price;
    private final int quantity;

    public OrderItem(double price, int quantity) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        this.price = price;
        this.quantity = quantity;
    }

    public double getLineTotal() {
        return price * quantity;
    }

    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}
