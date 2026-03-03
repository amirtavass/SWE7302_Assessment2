package com.cinema.modern.builder;

import com.cinema.modern.core.ITicket;
import com.cinema.modern.core.StandardTicket;
import com.cinema.modern.core.ImaxTicket;
import com.cinema.modern.decorator.GlassesDecorator;
import com.cinema.modern.decorator.PopcornDecorator;

public class TicketBuilder {

    private ITicket ticket;

    //using builder pattern to set the Ticket (between Standard or IMAX)
    public TicketBuilder setType(String type) {
        if (type.equalsIgnoreCase("IMAX")) {
            this.ticket = new ImaxTicket();
        } else {

            this.ticket = new StandardTicket();
        }
        return this;
    }

    //adding add ones like popcorn and 3d glasses using builder
    public TicketBuilder addPopcorn() {
        verifyTicketExists();

        this.ticket = new PopcornDecorator(this.ticket);
        return this;
    }


    public TicketBuilder addGlasses() {
        verifyTicketExists();
        this.ticket = new GlassesDecorator(this.ticket);
        return this;
    }

    //in last step we build the ticket
    public ITicket build() {
        verifyTicketExists();
        return this.ticket;
    }

    // purpose of this method is to prevent adding toppings if we have a null ticket
    private void verifyTicketExists() {
        if (this.ticket == null) {
            throw new IllegalStateException("Ticket not initialized. Call setType() first.");
        }
    }
}