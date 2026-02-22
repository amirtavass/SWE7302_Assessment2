package com.cinema.modern;

import com.cinema.modern.db.BookingDAO;
import com.cinema.modern.core.ITicket;
import com.cinema.modern.factory.TicketFactory;

import java.util.List;
import java.util.Scanner;

public class ModernMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // This initializes the Singleton DB connection automatically behind the scenes
        BookingDAO bookingDAO = new BookingDAO();

        System.out.println("--- Welcome to StarScreen Cinema ---");

        // 1. Fetch and display movies from the Database
        System.out.println("\nCurrently Showing:");
        List<String> movies = bookingDAO.getAvailableMovies();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }

        // 2. Ask user to select a movie
        System.out.print("\nEnter the number of the movie you want to see: ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (movieChoice < 1 || movieChoice > movies.size()) {
            System.out.println("Invalid selection. Exiting system.");
            return;
        }
        String selectedMovie = movies.get(movieChoice - 1);

        // 3. Ask user to select a ticket type
        System.out.println("\nSelect Ticket Type:");
        System.out.println("1. Standard (£10.00)");
        System.out.println("2. IMAX (£15.00)");
        System.out.print("Enter choice (1 or 2): ");
        int ticketChoice = scanner.nextInt();

        // Convert choice to the string our Factory expects
        String ticketType = (ticketChoice == 2) ? "IMAX" : "Standard";

        // 4. Create the ticket using the Factory Pattern
        ITicket ticket = TicketFactory.createTicket(ticketType);

        // Prepare the final booking details
        String bookingDescription = ticket.getDescription() + " for '" + selectedMovie + "'";
        double finalPrice = ticket.getPrice();

        // 5. Display Summary and Save to DB
        System.out.println("\n--- Booking Summary ---");
        System.out.println("Details: " + bookingDescription);
        System.out.println("Total Due: £" + String.format("%.2f", finalPrice));

        System.out.print("\nConfirm booking? (Y/N): ");
        String confirm = scanner.next();

        if (confirm.equalsIgnoreCase("Y")) {
            // Save to SQLite
            bookingDAO.saveBooking(bookingDescription, finalPrice);
            System.out.println("Booking successful! Saved to database.");
        } else {
            System.out.println("Booking cancelled.");
        }

        scanner.close();
    }
}