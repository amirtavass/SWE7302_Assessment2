package com.cinema.modern.observer;

public class EmailNotificationService implements BookingObserver {
    @Override
    public String update(String bookingDescription, double price) {

        return "📧 [Email Service] E-ticket generated and sent for: " + bookingDescription;
    }
}