package com.cinema.modern.decorator;

import com.cinema.modern.core.ITicket;

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