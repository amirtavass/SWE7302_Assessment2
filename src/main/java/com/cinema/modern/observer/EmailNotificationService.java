package com.cinema.modern.observer;

/**
 * this file handles sending confirmation emails when a ticket is successfully booked using Strategy pattern
 * This keeps confirmation logic separate from the booking flow.
 */

public class EmailNotificationService implements BookingObserver {
    @Override
    public String update(String bookingDescription, double price) {

        return "📧 [Email Service] E-ticket generated and sent for: " + bookingDescription;
    }
}