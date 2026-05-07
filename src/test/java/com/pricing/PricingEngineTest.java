package com.pricing;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PricingEngineTest {

    private final PricingEngine engine = new PricingEngine();

    @Test
    void testRegularCustomerNoDiscount() {
        List<Double> prices = Arrays.asList(100.0, 50.0);
        List<Integer> quantities = Arrays.asList(1, 2);
        // subtotal = 100 + 100 = 200, tax 20% = 40, final = 240
        double result = engine.calc(prices, quantities, "REGULAR", null);
        assertEquals(240.0, result, 0.001);
    }

    @Test
    void testVipCustomerNoDiscount() {
        List<Double> prices = Arrays.asList(200.0);
        List<Integer> quantities = Arrays.asList(1);
        // subtotal = 200, tax 10% = 20, final = 220
        double result = engine.calc(prices, quantities, "VIP", null);
        assertEquals(220.0, result, 0.001);
    }

    @Test
    void testRegularCustomerWithSave10() {
        List<Double> prices = Arrays.asList(100.0);
        List<Integer> quantities = Arrays.asList(1);
        // subtotal=100, disc=10, after=90, tax=18, final=108
        double result = engine.calc(prices, quantities, "REGULAR", "SAVE10");
        assertEquals(108.0, result, 0.001);
    }

    @Test
    void testVipCustomerWithSave20() {
        List<Double> prices = Arrays.asList(100.0);
        List<Integer> quantities = Arrays.asList(1);
        // subtotal=100, disc=20, after=80, tax=8, final=88
        double result = engine.calc(prices, quantities, "VIP", "SAVE20");
        assertEquals(88.0, result, 0.001);
    }

    @Test
    void testRegularCustomerWithSave30() {
        List<Double> prices = Arrays.asList(50.0, 50.0);
        List<Integer> quantities = Arrays.asList(2, 2);
        // subtotal=200, disc=60, after=140, tax=28, final=168
        double result = engine.calc(prices, quantities, "REGULAR", "SAVE30");
        assertEquals(168.0, result, 0.001);
    }

    @Test
    void testUnknownDiscountCodeIgnored() {
        List<Double> prices = Arrays.asList(100.0);
        List<Integer> quantities = Arrays.asList(1);
        // unknown code => no discount, REGULAR tax 20% => 120
        double result = engine.calc(prices, quantities, "REGULAR", "UNKNOWN");
        assertEquals(120.0, result, 0.001);
    }
}
