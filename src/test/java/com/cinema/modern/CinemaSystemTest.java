package com.cinema.modern;

import com.cinema.modern.db.DatabaseHandler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * using jupiter we test Basic checks for the database.
 * These tests make sure the DatabaseHandler acts as a singleton
 * and the database connection is properly established.
 */
public class CinemaSystemTest {

    @Test
    public void testDatabaseSingletonInstance() {
        //Asking for the database handler twice
        DatabaseHandler instance1 = DatabaseHandler.getInstance();
        DatabaseHandler instance2 = DatabaseHandler.getInstance();

        //Both references should point to the same instance
        assertNotNull(instance1, "DatabaseHandler instance should not be null");
        assertSame(instance1, instance2, "DatabaseHandler should rigidly follow the Singleton pattern");
    }

    @Test
    public void testDatabaseConnectionIsValid() {
        //Get the shared database handler
        DatabaseHandler dbHandler = DatabaseHandler.getInstance();

        //Try to obtain a database connection
        Connection conn = dbHandler.getConnection();

        //The connection should exist and be usable
        assertNotNull(conn, "A valid Database connection should be established");
        try {
            assertFalse(conn.isClosed(), "The database connection should remain open and active");
        } catch (SQLException e) {
            fail("SQLException thrown while checking connection status: " + e.getMessage());
        }
    }
}