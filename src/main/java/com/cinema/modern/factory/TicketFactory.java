package com.cinema.modern.factory;

import com.cinema.modern.core.ITicket;
import com.cinema.modern.core.ImaxTicket;
import com.cinema.modern.core.StandardTicket;

public class TicketFactory {


    public static ITicket createTicket(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Ticket type cannot be null");
        }

        return switch (type.toUpperCase()) {
            case "STANDARD" -> new StandardTicket();
            case "IMAX" -> new ImaxTicket();

            default -> throw new IllegalArgumentException("Unknown ticket type: " + type);
        };
    }
}