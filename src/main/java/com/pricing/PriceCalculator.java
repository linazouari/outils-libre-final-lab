package com.pricing;

import java.util.List;

public class PriceCalculator {

    public double calculateSubtotal(List<OrderItem> items) {
        return items.stream()
                .mapToDouble(OrderItem::getLineTotal)
                .sum();
    }

    public double applyDiscount(double subtotal, DiscountCode discount) {
        return subtotal * discount.getRate();
    }

    public double calculateTax(double amountAfterDiscount, CustomerType customerType) {
        return amountAfterDiscount * customerType.getTaxRate();
    }

    public PricingSummary calculate(List<OrderItem> items, CustomerType customerType, DiscountCode discount) {
        double subtotal = calculateSubtotal(items);
        double discountAmount = applyDiscount(subtotal, discount);
        double afterDiscount = subtotal - discountAmount;
        double tax = calculateTax(afterDiscount, customerType);
        double finalPrice = afterDiscount + tax;

        return new PricingSummary(subtotal, discountAmount, tax, finalPrice);
    }
}
