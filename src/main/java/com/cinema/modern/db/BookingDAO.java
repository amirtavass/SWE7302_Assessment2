package com.cinema.modern.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//using same logic of workshop2

public class BookingDAO {
    private DatabaseHandler db;

    public BookingDAO() {
        //  SINGLETON DB CONNECTION
        this.db = DatabaseHandler.getInstance();
    }

    // Reading seed data from the database
    public List<String> getAvailableMovies() {
        List<String> movies = new ArrayList<>();
        String sql = "SELECT title FROM movies";

        try (Statement stmt = db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                movies.add(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // Saving the final ticket details
    public void saveBooking(String ticketDesc, double totalPrice) {
        String sql = "INSERT INTO bookings(ticket_desc, total_price) VALUES(?, ?)";

        try (PreparedStatement pstmt = db.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, ticketDesc);
            pstmt.setDouble(2, totalPrice);
            pstmt.executeUpdate();
            System.out.println("[DAO] Saved booking to database: " + ticketDesc + " | £" + totalPrice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayPreviousBookings() {
        String sql = "SELECT * FROM bookings";

        try (Statement stmt = db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Previous Bookings ---");
            boolean hasBookings = false;

            while (rs.next()) {
                hasBookings = true;
                System.out.printf("ID: %d | Details: %s | Total: £%.2f\n",
                        rs.getInt("id"),
                        rs.getString("ticket_desc"),
                        rs.getDouble("total_price"));
            }

            if (!hasBookings) {
                System.out.println("No bookings found in the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching bookings: " + e.getMessage());
        }
    }
}