package com.cinema.modern;

import com.cinema.modern.api.CinemaApiFacade;
import java.util.List;
import java.util.Scanner;

public class ModernMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CinemaApiFacade api = new CinemaApiFacade();

        while (true) {
            System.out.println("\n====================================");
            System.out.println("   🎬 StarScreen Cinema Manager 🎬   ");
            System.out.println("====================================");
            System.out.println("1. Book a Ticket");
            System.out.println("2. View Previous Bookings");
            System.out.println("3. Exit System");
            System.out.print("Please select an option (1-3): ");

            String menuChoice = scanner.nextLine().trim();

            if (menuChoice.equals("2")) {
                api.showPreviousBookings();
                continue;
            } else if (menuChoice.equals("3")) {
                System.out.println("Exiting system. Goodbye!");
                break;
            } else if (!menuChoice.equals("1")) {
                System.out.println("❌ Invalid choice. Please try again.");
                continue;
            }

            // Collect user choices from the terminal
            System.out.println("\nCurrently Showing:");
            List<String> movies = api.getAvailableMovies();
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }

            System.out.print("\nEnter movie number: ");
            int movieChoice;
            try {
                movieChoice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number. Returning to menu.");
                continue;
            }

            if (movieChoice < 1 || movieChoice > movies.size()) {
                System.out.println("❌ Invalid selection. Returning to menu.");
                continue;
            }
            String selectedMovie = movies.get(movieChoice - 1);


            String ticketType = getTicketTypeInput(scanner);

            // ONLY YES/NO VALIDATION
            boolean wantsPopcorn = getYesNoInput(scanner, "\nAdd Large Popcorn for £6.50? (Y/N): ");
            boolean wantsGlasses = getYesNoInput(scanner, "Add 3D Glasses for £2.00? (Y/N): ");
            boolean isStudent = getYesNoInput(scanner, "\nAre you a Student? (20% Discount) (Y/N): ");

            // central entry point for booking workflow(Facade Pattern)
            String result = api.bookTicket(selectedMovie, ticketType, wantsPopcorn, wantsGlasses, isStudent);

            System.out.println("\n--- Booking Result ---");
            System.out.println(result);
        }
        scanner.close();
    }

    //VALIDATION FOR CHOICES
    private static String getTicketTypeInput(Scanner scanner) {
        while (true) {
            System.out.println("\nTicket Types: 1. Standard (£10.00) | 2. IMAX (£15.00)");
            System.out.print("Enter choice (1 or 2): ");
            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                return "Standard";
            } else if (input.equals("2")) {
                return "IMAX";
            }

            System.out.println("❌ Invalid input. Please type '1' for Standard or '2' for IMAX.");
        }
    }

    // VALIDATION FOR ONLY ACCEPTING U/N
    private static boolean getYesNoInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Y")) {
                return true;
            } else if (input.equals("N")) {
                return false;
            }

            System.out.println("❌ Invalid input. Please type 'Y' for Yes or 'N' for No.");
        }
    }
}