package com.cinema.modern.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private DatabaseHandler db;

    public BookingDAO() {
        // Obtains the Singleton database connection
        this.db = DatabaseHandler.getInstance();
    }

    // Reads the seeded movies from the database
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

    // Saves the finalized ticket details to the database
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
}