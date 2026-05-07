# Pricing & Discount Engine

A Gradle-based Java project built as part of the Open Source Tools Lab.

## Project Structure

```
pricing-engine/
├── build.gradle
├── settings.gradle
├── .gitignore
└── src/
    ├── main/java/com/pricing/
    │   ├── PricingEngine.java      # (starter — bad design)
    │   ├── CustomerType.java       # (refactored)
    │   ├── DiscountCode.java       # (refactored)
    │   ├── OrderItem.java          # (refactored)
    │   ├── PriceCalculator.java    # (refactored)
    │   └── PricingSummary.java     # (refactored)
    ├── test/java/com/pricing/
    │   ├── PricingEngineTest.java  # initial tests
    │   └── PriceCalculatorTest.java# refactored tests
    └── integration/
        └── test_pricing.py         # Python integration tests
```

## How to Build & Test

```bash
# Build
./gradlew build

# Run unit tests
./gradlew test

# Run integration tests
python3 src/integration/test_pricing.py
```

## Inputs

- List of item prices & quantities
- Customer type: `REGULAR` or `VIP`
- Discount code: `SAVE10`, `SAVE20`, `SAVE30`, or `null`

## Outputs

- Subtotal
- Discount amount
- Tax (20% for REGULAR, 10% for VIP)
- Final price
