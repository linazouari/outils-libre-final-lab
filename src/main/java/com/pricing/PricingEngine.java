package com.pricing;

import java.util.List;

// BAD DESIGN - starter code (will be refactored in later commits)
public class PricingEngine {

    public double calc(List<Double> prices, List<Integer> quantities,
                       String customerType, String discountCode) {
        double total = 0;
        for (int i = 0; i < prices.size(); i++) {
            total = total + prices.get(i) * quantities.get(i);
        }

        double disc = 0;
        if (discountCode != null) {
            if (discountCode.equals("SAVE10")) {
                disc = total * 0.10;
            } else if (discountCode.equals("SAVE20")) {
                disc = total * 0.20;
            } else if (discountCode.equals("SAVE30")) {
                disc = total * 0.30;
            }
        }

        double afterDisc = total - disc;

        double tax = 0;
        if (customerType.equals("REGULAR")) {
            tax = afterDisc * 0.20;
        } else if (customerType.equals("VIP")) {
            tax = afterDisc * 0.10;
        }

        double finalPrice = afterDisc + tax;
        return finalPrice;
    }
}
