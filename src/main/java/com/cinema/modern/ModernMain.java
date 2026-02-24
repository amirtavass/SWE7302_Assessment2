package com.cinema.modern;

import com.cinema.modern.api.CinemaApiFacade;
import java.util.List;
import java.util.Scanner;

public class ModernMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // We only interact with the Facade now!
        CinemaApiFacade api = new CinemaApiFacade();

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
                api.showPreviousBookings();
                continue;
            } else if (menuChoice.equals("3")) {
                System.out.println("Exiting system. Goodbye!");
                break;
            } else if (!menuChoice.equals("1")) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            // --- UI DATA COLLECTION ---
            System.out.println("\nCurrently Showing:");
            List<String> movies = api.getAvailableMovies();
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }

            System.out.print("\nEnter movie number: ");
            int movieChoice = Integer.parseInt(scanner.nextLine());
            String selectedMovie = movies.get(movieChoice - 1);

            System.out.println("\nTicket Types: 1. Standard (£10.00) | 2. IMAX (£15.00)");
            System.out.print("Enter choice (1 or 2): ");
            String ticketType = (Integer.parseInt(scanner.nextLine()) == 2) ? "IMAX" : "Standard";

            System.out.print("\nAdd Large Popcorn for £6.50? (Y/N): ");
            boolean wantsPopcorn = scanner.nextLine().equalsIgnoreCase("Y");

            System.out.print("Add 3D Glasses for £2.00? (Y/N): ");
            boolean wantsGlasses = scanner.nextLine().equalsIgnoreCase("Y");

            System.out.print("\nAre you a Student? (20% Discount) (Y/N): ");
            boolean isStudent = scanner.nextLine().equalsIgnoreCase("Y");

            // --- THE FACADE CALL ---
            // Instead of writing 20 lines of complex logic, the UI just calls the API!
            String result = api.bookTicket(selectedMovie, ticketType, wantsPopcorn, wantsGlasses, isStudent);

            System.out.println("\n--- Booking Result ---");
            System.out.println(result);
        }
        scanner.close();
    }
}