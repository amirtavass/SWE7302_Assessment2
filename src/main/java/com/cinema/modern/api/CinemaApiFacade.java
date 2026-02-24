package com.cinema.modern.api;

import com.cinema.modern.db.BookingDAO;
import com.cinema.modern.core.ITicket;
import com.cinema.modern.decorator.GlassesDecorator;
import com.cinema.modern.decorator.PopcornDecorator;
import com.cinema.modern.factory.TicketFactory;
import com.cinema.modern.strategy.PricingStrategy;
import com.cinema.modern.strategy.RegularPricingStrategy;
import com.cinema.modern.strategy.StudentStrategy;

import java.util.List;

public class CinemaApiFacade {
    private final BookingDAO bookingDAO;

    public CinemaApiFacade() {
        // Initializes the Singleton Database Connection
        this.bookingDAO = new BookingDAO();
    }

    public List<String> getAvailableMovies() {
        return bookingDAO.getAvailableMovies();
    }

    public void showPreviousBookings() {
        bookingDAO.displayPreviousBookings();
    }

    // The Facade Method: Hides all the complex Design Pattern logic from the UI!
    public String bookTicket(String movieName, String ticketType, boolean wantsPopcorn, boolean wantsGlasses, boolean isStudent) {

        // 1. Creational: Factory Pattern
        ITicket ticket = TicketFactory.createTicket(ticketType);

        // 2. Structural: Decorator Pattern
        if (wantsPopcorn) {
            ticket = new PopcornDecorator(ticket);
        }
        if (wantsGlasses) {
            ticket = new GlassesDecorator(ticket);
        }

        // 3. Behavioral: Strategy Pattern
        PricingStrategy pricingStrategy = isStudent ? new StudentStrategy() : new RegularPricingStrategy();

        double finalPrice = pricingStrategy.calculateFinalPrice(ticket.getPrice());
        String description = ticket.getDescription() + " [" + pricingStrategy.getDiscountDescription() + "] for '" + movieName + "'";

        // 4. Persistence: DAO Pattern
        bookingDAO.saveBooking(description, finalPrice);

        return "✅ Success! Booked: " + description + " | Total Paid: £" + String.format("%.2f", finalPrice);
    }
}