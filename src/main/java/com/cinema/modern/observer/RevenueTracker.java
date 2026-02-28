package com.cinema.modern.observer;

public class RevenueTracker implements BookingObserver {
    private double totalRevenue = 0.0;

    @Override
    public String update(String bookingDescription, double price) {
        totalRevenue += price;
        //%.2f Converts a double into two decimal places
        return "📈 [Revenue Tracker] Added £" + String.format("%.2f", price) +
                " to daily revenue. (Total Vault: £" + String.format("%.2f", totalRevenue) + ")";
    }
}