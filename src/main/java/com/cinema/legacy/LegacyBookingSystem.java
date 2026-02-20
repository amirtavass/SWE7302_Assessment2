package com.cinema.legacy;

import java.util.ArrayList;
import java.util.List;

public class LegacyBookingSystem {
    private List<String> bookingDetails = new ArrayList<>();
    private double basePrice = 0.0;
    private double totalCost = 0.0;

    // VIOLATION: Hardcoded object creation and pricing
    public void selectTicket(String screenType) {
        if (screenType.equalsIgnoreCase("Standard")) {
            basePrice = 10.00;
            bookingDetails.add("Standard 2D Ticket");
        } else if (screenType.equalsIgnoreCase("IMAX")) {
            basePrice = 15.00;
            bookingDetails.add("IMAX Ticket");
        } else {
            System.out.println("Error: Screen type not recognized.");
            return;
        }
        totalCost += basePrice;
        System.out.println("Selected: " + bookingDetails.get(0) + " - £" + basePrice);
    }

    // VIOLATION: String matching for add-ons (Class Explosion risk)
    public void addExtra(String extra) {
        if (extra.equalsIgnoreCase("3DGlasses")) {
            totalCost += 2.00;
            bookingDetails.add("+ 3D Glasses");
            System.out.println("Added 3D Glasses for £2.00");
        } else if (extra.equalsIgnoreCase("PopcornCombo")) {
            totalCost += 6.50;
            bookingDetails.add("+ Popcorn & Drink");
            System.out.println("Added Popcorn Combo for £6.50");
        }
    }

    // VIOLATION: Rigid discount rules embedded in checkout logic
    public double checkout(String discountCode) {
        double finalPrice = totalCost;

        System.out.println("\n--- Finalizing Booking ---");
        if (discountCode.equalsIgnoreCase("STUDENT")) {
            System.out.println("Applying 20% Student Discount...");
            finalPrice = totalCost * 0.80;
        } else if (discountCode.equalsIgnoreCase("TUESDAY_SPECIAL")) {
            System.out.println("Applying £3.00 Tuesday Discount...");
            finalPrice = totalCost - 3.00;
        } else {
            System.out.println("No discounts applied.");
        }

        System.out.println("Total Amount Due: £" + String.format("%.2f", finalPrice));
        return finalPrice;
    }
}