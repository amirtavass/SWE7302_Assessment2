package com.cinema.modern.api;

import com.cinema.modern.db.BookingDAO;
import com.cinema.modern.core.ITicket;
import com.cinema.modern.decorator.GlassesDecorator;
import com.cinema.modern.decorator.PopcornDecorator;
import com.cinema.modern.factory.TicketFactory;
import com.cinema.modern.strategy.PricingStrategy;
import com.cinema.modern.strategy.RegularPricingStrategy;
import com.cinema.modern.strategy.StudentStrategy;
import com.cinema.modern.observer.BookingEventManager;
import com.cinema.modern.observer.EmailNotificationService;
import com.cinema.modern.observer.RevenueTracker;

import java.util.List;

public class CinemaApiFacade {
    private final BookingDAO bookingDAO;
    private final BookingEventManager eventManager;

    public CinemaApiFacade() {
        this.bookingDAO = new BookingDAO();
        this.eventManager = new BookingEventManager();

        //Hook up all the systems that should react when a booking happens (emails, revenue tracking, etc.)
        eventManager.subscribe(new EmailNotificationService());
        eventManager.subscribe(new RevenueTracker());
    }

    public List<String> getAvailableMovies() {
        return bookingDAO.getAvailableMovies();
    }

    public void showPreviousBookings() {
        bookingDAO.displayPreviousBookings();
    }

    public String bookTicket(String movieName, String ticketType, boolean wantsPopcorn, boolean wantsGlasses, boolean isStudent) {

        // Create the ticket using the Factory pattern (Creational: Factory)
        ITicket ticket = TicketFactory.createTicket(ticketType);

        // Decorate the ticket with optional add-ons (Structural: Decorator)
        if (wantsPopcorn) {
            ticket = new PopcornDecorator(ticket);
        }
        if (wantsGlasses) {
            ticket = new GlassesDecorator(ticket);
        }

        // Apply the appropriate pricing strategy (Behavioral: Strategy)
        PricingStrategy pricingStrategy = isStudent ? new StudentStrategy() : new RegularPricingStrategy();
        double finalPrice = pricingStrategy.calculateFinalPrice(ticket.getPrice());
        String description = ticket.getDescription() + " [" + pricingStrategy.getDiscountDescription() + "] for '" + movieName + "'";

        //isolate database logic from business logic(Persistence: DAO)
        bookingDAO.saveBooking(description, finalPrice);

        // Notify all subscribed observers about the new booking (Behavioral: Strategy)
        List<String> observerLogs = eventManager.notifySubscribers(description, finalPrice);


        StringBuilder finalResult = new StringBuilder();
        finalResult.append("✅ Success! Booked: ").append(description)
                .append("\n💰 Total Paid: £").append(String.format("%.2f", finalPrice)).append("\n");

        for (String log : observerLogs) {
            finalResult.append("   -> ").append(log).append("\n");
        }

        return finalResult.toString();
    }
}