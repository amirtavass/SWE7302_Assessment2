package com.cinema.modern.strategy;

/**

 * The purpose of this class is to apply the student discount, keeping all discount logic
 *  logic separate from creating the ticket and booking flow.

 */
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