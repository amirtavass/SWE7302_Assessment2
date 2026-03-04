package com.cinema.modern;

import com.cinema.modern.builder.TicketBuilder;
import com.cinema.modern.core.ITicket;
import com.cinema.modern.db.DatabaseHandler;
import com.cinema.modern.strategy.PricingStrategy;
import com.cinema.modern.strategy.RegularPricingStrategy;
import com.cinema.modern.strategy.StudentStrategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * using jupiter we test Basic checks for the database.
 * These tests make sure the DatabaseHandler acts as a singleton
 * and the database connection is properly established.
 * v2:added Testing for the Cinema System dashboard.
 */
public class CinemaSystemTest {

    //DB TESTS(Singleton Pattern)
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

    // DESIGN PATTERN TESTS
    @Test
    public void testBuilderAndDecoratorPatterns() {
        //testing to see if the Builder correctly applies Decorators.
        TicketBuilder builder = new TicketBuilder();

         //EXAMPLE SENARIO
        // Base IMAX = £15.00
        // Popcorn = £6.50
        // Glasses = £2.00
        // Total Expected = £23.50

        ITicket complexTicket = builder.setType("IMAX")
                .addPopcorn()
                .addGlasses()
                .build();

        // Check Price Verification
        assertEquals(23.50, complexTicket.getPrice(), 0.01, "Builder should correctly sum the base ticket and decorators");

        // Check Description Verification
        assertTrue(complexTicket.getDescription().contains("IMAX"), "Ticket should be an IMAX ticket");
        assertTrue(complexTicket.getDescription().contains("Large Popcorn"), "Ticket should include Popcorn decorator");
        assertTrue(complexTicket.getDescription().contains("3D Glasses"), "Ticket should include Glasses decorator");
    }

    @Test
    public void testStudentPricingStrategy() {
        // Test the Behavioral Strategy Pattern (20% discount)
        PricingStrategy studentStrategy = new StudentStrategy();

        double basePrice = 10.00;
        double expectedDiscountedPrice = 8.00; // 20% off £10

        double actualPrice = studentStrategy.calculateFinalPrice(basePrice);

        assertEquals(expectedDiscountedPrice, actualPrice, 0.01, "Student Strategy should accurately apply a 20% discount");
        assertEquals("Student 20% Off", studentStrategy.getDiscountDescription(), "Description should match strategy type");
    }

    @Test
    public void testRegularPricingStrategy() {
        // Test that Regular pricing applies NO discount
        PricingStrategy regularStrategy = new RegularPricingStrategy();

        double basePrice = 15.00; // e.g., IMAX

        double actualPrice = regularStrategy.calculateFinalPrice(basePrice);

        assertEquals(15.00, actualPrice, 0.01, "Regular Strategy should not alter the base price");
        assertEquals("Regular Adult", regularStrategy.getDiscountDescription(), "Description should match standard pricing");
    }

}

