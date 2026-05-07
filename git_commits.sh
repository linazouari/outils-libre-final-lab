#!/bin/bash
# ============================================================
# Run this script ONCE from inside your pricing-engine folder.
# It creates all commits exactly as the professor expects.
# ============================================================

set -e  # stop on any error

# ── 0. Init repo (skip if already done) ──────────────────────
git init
git branch -M main

# ── COMMIT 1 ─────────────────────────────────────────────────
# Add Gradle project skeleton
git add build.gradle settings.gradle .gitignore README.md
git commit -m "feat: initialize Gradle project structure"

# ── COMMIT 2 ─────────────────────────────────────────────────
# Add bad-design starter code
git add src/main/java/com/pricing/PricingEngine.java
git commit -m "feat: add bad-design starter PricingEngine class"

# ── COMMIT 3 ─────────────────────────────────────────────────
# Add initial unit tests (written against bad design)
git add src/test/java/com/pricing/PricingEngineTest.java
git commit -m "test: add initial JUnit tests for PricingEngine"

# ── COMMIT 4 ─────────────────────────────────────────────────
# Refactor – introduce enums and value objects
git add src/main/java/com/pricing/CustomerType.java
git add src/main/java/com/pricing/DiscountCode.java
git add src/main/java/com/pricing/OrderItem.java
git add src/main/java/com/pricing/PricingSummary.java
git commit -m "refactor: extract CustomerType, DiscountCode, OrderItem, PricingSummary"

# ── COMMIT 5 ─────────────────────────────────────────────────
# Refactor – replace monolithic method with PriceCalculator
git add src/main/java/com/pricing/PriceCalculator.java
git commit -m "refactor: introduce PriceCalculator with single-responsibility methods"

# ── COMMIT 6 ─────────────────────────────────────────────────
# Update tests to cover refactored classes
git add src/test/java/com/pricing/PriceCalculatorTest.java
git commit -m "test: add comprehensive JUnit tests for refactored PriceCalculator"

# ── COMMIT 7 ─────────────────────────────────────────────────
# Add Python integration tests
git add src/integration/test_pricing.py
git commit -m "test: add Python integration tests for pricing logic"

echo ""
echo "========================================"
echo " All 7 commits created successfully!"
echo "========================================"
echo ""
echo "Now push to your fork:"
echo "  git remote add origin https://github.com/linazouari/outils-libre-final-lab.git"
echo "  git push -u origin main"
