package com.cinema.modern.strategy;

public interface PricingStrategy {
    double calculateFinalPrice(double basePrice);
    String getDiscountDescription();
}