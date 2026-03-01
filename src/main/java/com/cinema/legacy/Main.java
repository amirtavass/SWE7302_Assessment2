package com.cinema.legacy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Legacy StarScreen\n");

        //Booking 1
        LegacyBookingSystem booking1 = new LegacyBookingSystem();
        booking1.selectTicket("IMAX");
        booking1.addExtra("3DGlasses");
        booking1.checkout("STUDENT");



        //Booking 2
        LegacyBookingSystem booking2 = new LegacyBookingSystem();
        booking2.selectTicket("Standard");
        booking2.addExtra("PopcornCombo");
        booking2.checkout("TUESDAYSPECIAL");
    }
}