# StarScreen Ticket Manager 🍿 v2.1

**Assessment 2: Advanced Software Development (SWE7302)**

## 📋 Project Overview
StarScreen Ticket Manager is an educational project that shows how to refactor poorly designed legacy code into a modern, maintainable architecture using design patterns.

The original(legacy) system was built as a single ,coupled design. It manages the logic of the project all within a single  class using  `if-else` chains. This project serves as a "before and after" showcase, the transformation into a modular, scalable architecture.

### 🛑 The "Before" State: Legacy Code

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


### ✅ Phase 3: Dynamic Add-ons (Decorator Pattern) & Interactive Menu (Completed)
* **Context:** The legacy `addExtra` method relied on fragile string comparisons to add 3D glasses or popcorn. Adding new snacks risked a "class explosion" or massive conditional blocks. Furthermore, the UI lacked a way to view historical data.
* **Goal:** Implemented a **Structural Pattern (Decorator)**. Created a base `TicketDecorator` and wrapped it dynamically with `PopcornDecorator` and `GlassesDecorator` to calculate prices seamlessly without altering the core `ITicket` implementations. Additionally, updated the main application loop to include an interactive menu that fetches and displays previous database bookings.


![img_3.png](img_3.png)

### 🚧 Phase 4: Pricing Rules & Strategy Pattern (In Progress)
Now that the core ticket creation, dynamic snacks, and database persistence are working, the next phase focuses on flexible pricing.
* **Context:** The legacy `checkout` method contains hardcoded math for Student and Tuesday discounts, violating the Single Responsibility Principle and Open/Closed Principle.
* **Goal:** Implement a **Behavioral Pattern (Strategy)**. Extract the discount math into a `PricingStrategy` interface with interchangeable classes (`StudentPricingStrategy`, `NoDiscountStrategy`) so new promotional rules can be added dynamically without modifying the core checkout logic.

## 👤 Author
**Amir Tavassoli**
MSc Software Engineering | University of Bolton

## 📄 License
This is an educational project created for portfolio and assessment purposes.