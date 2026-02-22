package com.cinema.modern.decorator;

import com.cinema.modern.core.ITicket;

public abstract class TicketDecorator implements ITicket {
    protected ITicket wrappedTicket;

    public TicketDecorator(ITicket ticket) {
        this.wrappedTicket = ticket;
    }

    @Override
    public String getDescription() {
        return wrappedTicket.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedTicket.getPrice();
    }
}