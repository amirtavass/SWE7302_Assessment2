package com.cinema.modern;

import com.cinema.modern.db.BookingDAO;
import com.cinema.modern.core.ITicket;
import com.cinema.modern.decorator.GlassesDecorator;
import com.cinema.modern.decorator.PopcornDecorator;
import com.cinema.modern.factory.TicketFactory;

import java.util.List;
import java.util.Scanner;

public class ModernMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingDAO bookingDAO = new BookingDAO();

        // Main Application Loop
        while (true) {
            System.out.println("\n====================================");
            System.out.println("   🎬 StarScreen Cinema Manager 🎬   ");
            System.out.println("====================================");
            System.out.println("1. Book a Ticket");
            System.out.println("2. View Previous Bookings");
            System.out.println("3. Exit System");
            System.out.print("Please select an option (1-3): ");

            String menuChoice = scanner.nextLine();

            if (menuChoice.equals("2")) {
                bookingDAO.displayPreviousBookings();
                continue; // Goes back to the start of the loop
            } else if (menuChoice.equals("3")) {
                System.out.println("Exiting system. Goodbye!");
                break; // Ends the program
            } else if (!menuChoice.equals("1")) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            // --- BOOKING FLOW (Option 1) ---
            System.out.println("\nCurrently Showing:");
            List<String> movies = bookingDAO.getAvailableMovies();
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }

            System.out.print("\nEnter the number of the movie you want to see: ");
            // We use Integer.parseInt to avoid the Scanner newline skipping bug
            int movieChoice = Integer.parseInt(scanner.nextLine());

            if (movieChoice < 1 || movieChoice > movies.size()) {
                System.out.println("Invalid selection. Returning to main menu.");
                continue;
            }
            String selectedMovie = movies.get(movieChoice - 1);

            // 1. Factory Pattern: Select Ticket
            System.out.println("\nSelect Ticket Type:");
            System.out.println("1. Standard (£10.00)");
            System.out.println("2. IMAX (£15.00)");
            System.out.print("Enter choice (1 or 2): ");
            int ticketChoice = Integer.parseInt(scanner.nextLine());

            String ticketType = (ticketChoice == 2) ? "IMAX" : "Standard";
            ITicket ticket = TicketFactory.createTicket(ticketType);

            // 2. Decorator Pattern: Add Snacks
            System.out.print("\nWould you like to add a Large Popcorn for £6.50? (Y/N): ");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                ticket = new PopcornDecorator(ticket); // Wraps the ticket
            }

            System.out.print("Would you like to add 3D Glasses for £2.00? (Y/N): ");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                ticket = new GlassesDecorator(ticket); // Wraps the ticket again
            }

            // 3. Display Summary and Save
            String bookingDescription = ticket.getDescription() + " for '" + selectedMovie + "'";
            double finalPrice = ticket.getPrice();

            System.out.println("\n--- Booking Summary ---");
            System.out.println("Details: " + bookingDescription);
            System.out.println("Total Due: £" + String.format("%.2f", finalPrice));

            System.out.print("\nConfirm booking? (Y/N): ");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                bookingDAO.saveBooking(bookingDescription, finalPrice);
                System.out.println("✅ Booking successful! Saved to database.");
            } else {
                System.out.println("❌ Booking cancelled.");
            }
        }

        scanner.close();
    }
}