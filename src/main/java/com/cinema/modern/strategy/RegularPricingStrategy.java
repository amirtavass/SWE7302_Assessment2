package com.cinema.modern.strategy;

public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double basePrice) {
        return basePrice;
    }

    @Override
    public String getDiscountDescription() {
        return "Standard Pricing";
    }
}