package com.cinema.modern;

import com.cinema.modern.api.CinemaApiFacade;
import java.util.List;
import java.util.Scanner;
import com.cinema.modern.builder.TicketBuilder;
import com.cinema.modern.core.ITicket;

public class ModernMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CinemaApiFacade api = new CinemaApiFacade();

        while (true) {

            System.out.println("    StarScreen Cinema Manager    ");
            System.out.println("1.Book a Ticket");
            System.out.println("2.View Previous Bookings");
            System.out.println("3.Exit System");
            System.out.print("Please select an option from 1 to 3: ");

            String menuChoice = scanner.nextLine().trim();

            if (menuChoice.equals("2")) {
                api.showPreviousBookings();
                continue;
            } else if (menuChoice.equals("3")) {
                System.out.println("Exiting system...goodbye!");
                break;
            } else if (!menuChoice.equals("1")) {
                System.out.println("Wrong choice,Please try again.");
                continue;
            }


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
                System.out.println("Wrong input. Please enter a number between the options.");
                continue;
            }

            if (movieChoice < 1 || movieChoice > movies.size()) {
                System.out.println("Wrong input. Please enter a number between the options.");
                continue;
            }
            String selectedMovie = movies.get(movieChoice - 1);


            String ticketType = getTicketTypeInput(scanner);

            // ONLY YES/NO VALIDATION
            boolean wantsPopcorn = getYesNoInput(scanner, "\nAdd a Large Popcorn for £6.50? (Y/N): ");
            boolean wantsGlasses = getYesNoInput(scanner, "Add 3D Glasses for £2.00? (Y/N): ");
            boolean isStudent = getYesNoInput(scanner, "\nAre you a Student? (20% Discount) (Y/N): ");

            // central entry point for booking workflow(Facade Pattern)
            /*
              LEGACY APPROACH:
              *In the legacy version, ticket options were passed as multiple separate parameters to the API.
              *If we added for example Soda add on, we'd have to change the method entirely in the API and it would break every other class using it.
            */


            //String result = api.bookTicket(selectedMovie, ticketType, wantsPopcorn, wantsGlasses, isStudent);


            /*
              BUILDER PATTERN:
              *The new version uses the Builder Pattern to make the ticket object step by step.
              *This improves readability of our code and keeps the API clean by passing a single object instead of multiple parameters.
             */

            //first we create the Builder
            TicketBuilder builder = new TicketBuilder();

            //then we Configure the ticket type
            builder.setType(ticketType); // "Standard" or "IMAX"

            //adding add ons after that
            if (wantsPopcorn) {
                builder.addPopcorn();
            }
            if (wantsGlasses) {
                builder.addGlasses();
            }

            //finally we Build the final object
            ITicket ticket = builder.build();

            //in the end we just Pass the object to the API
            String result = api.bookTicket(ticket, selectedMovie, isStudent);

            System.out.println("\n Booking Result");
            System.out.println(result);
        }
        scanner.close();
    }

    //VALIDATION FOR CHOICES
    private static String getTicketTypeInput(Scanner scanner) {
        while (true) {
            System.out.println("\nTicket options: 1. Standard ticket(£10.00) | 2. IMAX ticket (£15.00)");
            System.out.print("Choose between 1 or 2: ");
            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                return "Standard";
            } else if (input.equals("2")) {
                return "IMAX";
            }

            System.out.println("Wrong input. Please type '1' for Standard ticket or '2' for IMAX ticket.");
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

            System.out.println("Wrong input. Please type either 'Y' for Yes and 'N' for No.");
        }
    }
}