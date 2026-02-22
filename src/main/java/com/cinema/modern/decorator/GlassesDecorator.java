package com.cinema.modern.decorator;

import com.cinema.modern.core.ITicket;

public class GlassesDecorator extends TicketDecorator {
    public GlassesDecorator(ITicket ticket) {
        super(ticket);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 3D Glasses";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 2.00;
    }
}