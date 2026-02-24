package com.cinema.modern.strategy;

public class StudentStrategy implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double basePrice) {
        return basePrice * 0.80; // 20% off
    }

    @Override
    public String getDiscountDescription() {
        return "Student Discount (20% off)";
    }
}