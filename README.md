<<<<<<< HEAD
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
=======
# Lab: Pricing & Discount Engine (Refactoring + Gradle + Testing)

## Objective
This lab combines:
- Git/GitHub workflow
- Refactoring of poor-quality code
- Gradle build system
- Java unit testing (JUnit)
- Python-based integration testing

Students start from a badly designed Java class and progressively improve it.

## Project Overview

Build a **pricing engine** that calculates the final price of an order.

### Inputs
- List of item prices
- Quantities
- Customer type (`REGULAR`, `VIP`)
- Discount code (`SAVE10`, `SAVE20`, etc.)

### Outputs
- Subtotal
- Discount amount
- Tax
- Final price

## Lab Workflow

1. Create a Gradle-based Java project  
2. Initialize a Git repository and push to GitHub  
3. Add the provided “bad design” starter code  
4. Write initial unit tests (JUnit)  
5. Refactor the code (improve structure, separation of concerns, readability)  
6. Continue committing at each logical step with clear messages
>>>>>>> 6ba70b411f46e4277c4b66a2fe4d9cac20eae80a
