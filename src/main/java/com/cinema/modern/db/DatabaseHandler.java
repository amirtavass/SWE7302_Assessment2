package com.cinema.modern.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

    private static DatabaseHandler instance;
    private Connection connection;

    // Private constructor to prevent direct instantiation
    private DatabaseHandler() {
        try {
            // Force Java to load the SQLite driver
            Class.forName("org.sqlite.JDBC");

            // Creates cinema.db file in the project root
            String url = "jdbc:sqlite:cinema.db";
            this.connection = DriverManager.getConnection(url);
            System.out.println("SQL Connection Established");
            initializeTables();

        } catch (ClassNotFoundException e) {
            System.err.println("CRITICAL: SQLite JDBC Driver not found! Please click 'Reload Maven Project' in IntelliJ.");
        } catch (SQLException e) {
            System.err.println("Database connection Error: " + e.getMessage());
        }
    }

    // Public method to get the single instance
    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    private void initializeTables() {
        // Safety check: Don't try to make tables if the connection failed
        if (connection == null) return;

        String createMoviesTable = "CREATE TABLE IF NOT EXISTS movies (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL" +
                ");";

        String createBookingsTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ticket_desc TEXT NOT NULL, " +
                "total_price REAL NOT NULL" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createMoviesTable);
            stmt.execute(createBookingsTable);

            // Seed the database with movies if it's empty
            String countMovies = "SELECT COUNT(*) AS count FROM movies";
            var rs = stmt.executeQuery(countMovies);
            if (rs.next() && rs.getInt("count") == 0) {
                stmt.execute("INSERT INTO movies (title) VALUES ('Dune: Part Two'), ('Oppenheimer'), ('Spider-Man: Across the Spider-Verse')");
                System.out.println("Seeded database with initial movies.");
            }
            System.out.println("Tables initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}