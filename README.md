# StarScreen Ticket Manager 🍿 v1.0

**Assessment 2: Advanced Software Development (SWE7302)**

## 📋 Project Overview
StarScreen Ticket Manager is an educational project that demonstrates how to refactor poorly designed legacy code into a modern, maintainable architecture using design patterns and SOLID principles.

The original system was developed rapidly as a monolith. It manages movie formats, snack add-ons, and promotional discounts all within a single rigid class using fragile `if-else` chains. This project serves as a "before and after" showcase, refactoring the system into a modular architecture.

### 🛑 The "Before" State: Legacy Code
*(The following screenshot demonstrates the monolithic, tightly coupled nature of the original system, featuring hardcoded ticket creation, rigid add-on logic, and embedded discount rules before refactoring.)*

![img.png](img.png)


## 🏗 Progress & Patterns


### 🚧 Phase 1: Ticket Instantiation & Factory Pattern (Completed)
* **Context:** The legacy `selectTicket` method uses hardcoded `if/else` logic to create standard or IMAX tickets.
* **Goal:** Implement a **Creational Pattern (Factory Method)** to cleanly instantiate `ImaxTicket` or `StandardTicket` objects, removing rigid dependencies and adhering to the Open/Closed Principle.

![img_1.png](img_1.png)



### ✅ Phase 2: Database Implementation (Singleton & DAO Patterns) (Completed)
* **Context:** The system needed a reliable way to persist completed bookings and fetch available movies without cluttering the main application logic with SQL queries or risking multiple database connections.
* **Goal:** Implemented the **Singleton Pattern** via `DatabaseHandler` to ensure only one active SQLite connection exists. Implemented the **Data Access Object (DAO) Pattern** via `BookingDAO` to separate database operations from the business logic, satisfying the Single Responsibility Principle.

![img_2.png](img_2.png)

### 🚧 Phase 3: Next Phases - Structural & Behavioral Patterns (In Progress)
Now that the core ticket creation and database persistence are working, the next phase focuses on expanding the system's features dynamically.

## 👤 Author
**Amir Tavassoli**
MSc Software Engineering | University of Bolton

## 📄 License
This is an educational project created for portfolio and assessment purposes.