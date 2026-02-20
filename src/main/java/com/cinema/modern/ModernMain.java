package com.cinema.modern;

import com.cinema.modern.factory.TicketFactory;
import com.cinema.modern.core.ITicket;

public class ModernMain {
    public static void main(String[] args) {
        System.out.println("--- Modern StarScreen System Initialized (Phase 1) ---\n");

        //  IMAX ticket using the Factory Pattern
        System.out.println("Processing Booking 1...");
        ITicket ticket1 = TicketFactory.createTicket("IMAX");
        System.out.println("Ticket Issued: " + ticket1.getDescription() + " | Cost: £" + ticket1.getPrice());

        System.out.println("\n---------------------------------------\n");

        // Standard ticket using the Factory Pattern
        System.out.println("Processing Booking 2...");
        ITicket ticket2 = TicketFactory.createTicket("Standard");
        System.out.println("Ticket Issued: " + ticket2.getDescription() + " | Cost: £" + ticket2.getPrice());

        System.out.println("\n(Note: Add-ons and Discounts will be implemented in Phases 2 & 3)");
    }
}