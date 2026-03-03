/**
 * The goal of API Facade for the cinema booking system.
 * This class sits between the user interface and the core business logic.
 * It hides all the messy details (database calls, ticket creation, pricing rules,
 * and event notifications) behind a simple, clean API.
 */


package com.cinema.modern.api;

import com.cinema.modern.db.BookingDAO;
import com.cinema.modern.core.ITicket;
import com.cinema.modern.strategy.PricingStrategy;
import com.cinema.modern.strategy.RegularPricingStrategy;
import com.cinema.modern.strategy.StudentStrategy;
import com.cinema.modern.observer.BookingEventManager;
import com.cinema.modern.observer.EmailNotificationService;
import com.cinema.modern.observer.RevenueTracker;


//after sending the builder obj of the ticket with all the details to this api,now this class focused mainly on processing the transaction (Pricing -> Saving -> Notifying).

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

    //so now in bookticket we accept the ticket obj instead of having booleans like wantsPopcorn,wantsGlasses.

    public String bookTicket(ITicket ticket,String movieName,boolean isStudent) {
        //Creational&Structural&Behavioral patterns


        // Applying the pricing strategy (Behavioral: Strategy)
        PricingStrategy pricingStrategy = isStudent ? new StudentStrategy() : new RegularPricingStrategy();


        // The ticket object already knows its own base price from ticket obj (including add-ons) so we just calculate final price
        double finalPrice = pricingStrategy.calculateFinalPrice(ticket.getPrice());

        String description = ticket.getDescription() + " [" + pricingStrategy.getDiscountDescription() + "] for '" + movieName + "'";

        //now we save it to db(Persistence: DAO)
        bookingDAO.saveBooking(description, finalPrice);

        // in here it notifies all subscribed observers about the new booking (Behavioral: Strategy)
        List<String> observerLogs = eventManager.notifySubscribers(description, finalPrice);

        //and finally creating the response
        StringBuilder finalResult = new StringBuilder();
        finalResult.append("Success! You Booked: ").append(description)
                .append("\n Total Paid is: £").append(String.format("%.2f", finalPrice)).append("\n");


        return finalResult.toString();
    }
}