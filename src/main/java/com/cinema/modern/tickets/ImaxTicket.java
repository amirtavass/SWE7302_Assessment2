package com.cinema.modern.tickets;

public class ImaxTicket implements ITicket {
    @Override
    public String getDescription() {
        return "IMAX Ticket";
    }

    @Override
    public double getPrice() {
        return 15.00;
    }
}