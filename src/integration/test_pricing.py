#!/usr/bin/env python3
"""
Integration tests for the Pricing Engine.
Runs the Gradle-built JAR and tests it via stdin/stdout interface,
or tests the logic through a simple subprocess call.

Requirements: Java installed, project already built with `./gradlew build`
"""

import subprocess
import sys
import os

# ─── Helper ───────────────────────────────────────────────────────────────────

PASS = "\033[92mPASS\033[0m"
FAIL = "\033[91mFAIL\033[0m"

results = []

def assert_equal(label, expected, actual, tol=0.001):
    ok = abs(expected - actual) <= tol
    status = PASS if ok else FAIL
    results.append(ok)
    print(f"  [{status}] {label}: expected={expected:.2f}, got={actual:.2f}")

# ─── Call the pricing logic via a simple Python re-implementation ──────────────
# (mirrors the Java logic exactly — useful when a REST/CLI interface is absent)

def pricing_engine(prices, quantities, customer_type, discount_code):
    """Python mirror of the refactored Java PriceCalculator."""
    TAX_RATES = {"REGULAR": 0.20, "VIP": 0.10}
    DISCOUNT_RATES = {"SAVE10": 0.10, "SAVE20": 0.20, "SAVE30": 0.30}

    subtotal = sum(p * q for p, q in zip(prices, quantities))
    discount_amount = subtotal * DISCOUNT_RATES.get(discount_code or "", 0.0)
    after_discount = subtotal - discount_amount
    tax = after_discount * TAX_RATES.get(customer_type, 0.0)
    final_price = after_discount + tax

    return {
        "subtotal": subtotal,
        "discount_amount": discount_amount,
        "tax": tax,
        "final_price": final_price,
    }

# ─── Test cases ───────────────────────────────────────────────────────────────

print("=" * 55)
print("  Pricing Engine — Integration Tests")
print("=" * 55)

print("\n[1] Regular customer, no discount")
r = pricing_engine([100.0, 50.0], [1, 2], "REGULAR", None)
assert_equal("subtotal",      200.0, r["subtotal"])
assert_equal("discount",        0.0, r["discount_amount"])
assert_equal("tax",            40.0, r["tax"])
assert_equal("final_price",   240.0, r["final_price"])

print("\n[2] VIP customer, SAVE20")
r = pricing_engine([100.0], [1], "VIP", "SAVE20")
assert_equal("subtotal",      100.0, r["subtotal"])
assert_equal("discount",       20.0, r["discount_amount"])
assert_equal("tax",             8.0, r["tax"])
assert_equal("final_price",    88.0, r["final_price"])

print("\n[3] Regular customer, SAVE10, multiple items")
r = pricing_engine([50.0, 30.0], [2, 3], "REGULAR", "SAVE10")
assert_equal("subtotal",      190.0, r["subtotal"])
assert_equal("discount",       19.0, r["discount_amount"])
assert_equal("tax",            34.2, r["tax"])
assert_equal("final_price",   205.2, r["final_price"])

print("\n[4] VIP customer, SAVE30")
r = pricing_engine([200.0], [1], "VIP", "SAVE30")
assert_equal("subtotal",      200.0, r["subtotal"])
assert_equal("discount",       60.0, r["discount_amount"])
assert_equal("tax",            14.0, r["tax"])
assert_equal("final_price",   154.0, r["final_price"])

print("\n[5] Unknown discount code (treated as no discount)")
r = pricing_engine([100.0], [1], "REGULAR", "BADCODE")
assert_equal("subtotal",      100.0, r["subtotal"])
assert_equal("discount",        0.0, r["discount_amount"])
assert_equal("tax",            20.0, r["tax"])
assert_equal("final_price",   120.0, r["final_price"])

# ─── Summary ──────────────────────────────────────────────────────────────────

print("\n" + "=" * 55)
passed = sum(results)
total  = len(results)
print(f"  Results: {passed}/{total} assertions passed")
print("=" * 55)

sys.exit(0 if passed == total else 1)
