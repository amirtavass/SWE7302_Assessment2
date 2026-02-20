package com.cinema.modern.tickets;

public class StandardTicket implements ITicket {
    @Override
    public String getDescription() {
        return "Standard 2D Ticket";
    }

    @Override
    public double getPrice() {
        return 10.00;
    }
}