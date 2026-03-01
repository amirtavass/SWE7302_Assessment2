package com.cinema.modern.decorator;

import com.cinema.modern.core.ITicket;

/**
 * in here we add the popcorn add-on as an optional add-on to a ticket using decorator pattern
 */

public class PopcornDecorator extends TicketDecorator {
    public PopcornDecorator(ITicket ticket) {
        super(ticket);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Large Popcorn";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 6.50;
    }
}