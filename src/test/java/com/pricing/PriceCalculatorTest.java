package com.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PriceCalculatorTest {

    private PriceCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new PriceCalculator();
    }

    // --- Subtotal tests ---

    @Test
    void testSubtotalSingleItem() {
        List<OrderItem> items = List.of(new OrderItem(100.0, 2));
        assertEquals(200.0, calculator.calculateSubtotal(items), 0.001);
    }

    @Test
    void testSubtotalMultipleItems() {
        List<OrderItem> items = Arrays.asList(
            new OrderItem(50.0, 2),
            new OrderItem(30.0, 3)
        );
        assertEquals(190.0, calculator.calculateSubtotal(items), 0.001);
    }

    // --- Discount tests ---

    @Test
    void testDiscountSave10() {
        assertEquals(10.0, calculator.applyDiscount(100.0, DiscountCode.SAVE10), 0.001);
    }

    @Test
    void testDiscountSave20() {
        assertEquals(20.0, calculator.applyDiscount(100.0, DiscountCode.SAVE20), 0.001);
    }

    @Test
    void testDiscountSave30() {
        assertEquals(30.0, calculator.applyDiscount(100.0, DiscountCode.SAVE30), 0.001);
    }

    @Test
    void testNoDiscount() {
        assertEquals(0.0, calculator.applyDiscount(100.0, DiscountCode.NONE), 0.001);
    }

    // --- Tax tests ---

    @Test
    void testTaxForRegularCustomer() {
        assertEquals(20.0, calculator.calculateTax(100.0, CustomerType.REGULAR), 0.001);
    }

    @Test
    void testTaxForVipCustomer() {
        assertEquals(10.0, calculator.calculateTax(100.0, CustomerType.VIP), 0.001);
    }

    // --- Full calculation tests ---

    @Test
    void testFullCalculationRegularNoDiscount() {
        List<OrderItem> items = List.of(new OrderItem(100.0, 2));
        // subtotal=200, disc=0, after=200, tax=40, final=240
        PricingSummary summary = calculator.calculate(items, CustomerType.REGULAR, DiscountCode.NONE);
        assertEquals(200.0, summary.getSubtotal(), 0.001);
        assertEquals(0.0,   summary.getDiscountAmount(), 0.001);
        assertEquals(40.0,  summary.getTax(), 0.001);
        assertEquals(240.0, summary.getFinalPrice(), 0.001);
    }

    @Test
    void testFullCalculationVipSave20() {
        List<OrderItem> items = List.of(new OrderItem(100.0, 1));
        // subtotal=100, disc=20, after=80, tax=8, final=88
        PricingSummary summary = calculator.calculate(items, CustomerType.VIP, DiscountCode.SAVE20);
        assertEquals(100.0, summary.getSubtotal(), 0.001);
        assertEquals(20.0,  summary.getDiscountAmount(), 0.001);
        assertEquals(8.0,   summary.getTax(), 0.001);
        assertEquals(88.0,  summary.getFinalPrice(), 0.001);
    }

    // --- DiscountCode.fromString tests ---

    @Test
    void testDiscountFromStringNull() {
        assertEquals(DiscountCode.NONE, DiscountCode.fromString(null));
    }

    @Test
    void testDiscountFromStringUnknown() {
        assertEquals(DiscountCode.NONE, DiscountCode.fromString("INVALID"));
    }

    @Test
    void testDiscountFromStringValid() {
        assertEquals(DiscountCode.SAVE10, DiscountCode.fromString("SAVE10"));
    }

    // --- OrderItem validation tests ---

    @Test
    void testOrderItemNegativePriceThrows() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem(-1.0, 1));
    }

    @Test
    void testOrderItemZeroQuantityThrows() {
        assertThrows(IllegalArgumentException.class, () -> new OrderItem(10.0, 0));
    }
}
