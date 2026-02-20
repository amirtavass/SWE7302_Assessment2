package com.cinema.legacy;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Legacy StarScreen System Initialized ---\n");

        // Booking 1: Student buying an IMAX ticket with 3D Glasses
        LegacyBookingSystem booking1 = new LegacyBookingSystem();
        booking1.selectTicket("IMAX");
        booking1.addExtra("3DGlasses");
        booking1.checkout("STUDENT");

        System.out.println("\n---------------------------------------\n");

        // Booking 2: Standard ticket on a Tuesday with Popcorn
        LegacyBookingSystem booking2 = new LegacyBookingSystem();
        booking2.selectTicket("Standard");
        booking2.addExtra("PopcornCombo");
        booking2.checkout("TUESDAY_SPECIAL");
    }
}