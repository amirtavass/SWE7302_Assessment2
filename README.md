# StarScreen Ticket Manager 🍿 v2.3

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

### ✅ Phase 4: Pricing Rules & Strategy Pattern (Completed)
Now that the core ticket creation, dynamic snacks, and database persistence are working, the next phase focuses on flexible pricing.
* **Context:** The legacy `checkout` method contains hardcoded math for Student discounts, violating the Single Responsibility Principle and Open/Closed Principle.
* **Goal:** Implemented a **Behavioral Pattern (Strategy)**. Extracted the discount math into a `PricingStrategy` interface with interchangeable classes (`StudentStrategy`, `RegularPricingStrategy`) so new promotional rules can be added dynamically without modifying the core checkout logic.

![img_4.png](img_4.png)


### ✅ Phase 5: System Decoupling & Facade Pattern (Completed)
* **Context:** The console UI (`ModernMain`) was becoming tightly coupled to multiple design pattern implementations (Factory, Decorator, Strategy) and the Database DAO, making it difficult to eventually migrate to a modern Web UI.
* **Goal:** Implemented a **Structural Pattern (Facade)** via `CinemaApiFacade`. This provides a single, simplified API entry point for booking tickets, hiding the complex subsystem logic. This cleanly separates the backend business rules from the frontend, perfectly preparing the application for an HTML/CSS/JS web integration.

### ✅ Phase 6: Post-Booking Events & Observer Pattern (Completed)
* **Context:** After a successful booking, the system needed to trigger secondary processes (like sending confirmation emails and tracking daily revenue). Hardcoding these steps into the core booking flow would violate the Single Responsibility Principle and create rigid, coupled code.
* **Goal:** Implemented a **Behavioral Pattern (Observer)**. Created a `BookingEventManager` (Publisher) and distinct listener classes (`EmailNotificationService`, `RevenueTracker`). When a ticket is booked, the Facade notifies the event manager, which automatically triggers all subscribed observers and collects their logs for the UI. This establishes an event-driven architecture, allowing new post-booking features to be added without modifying the core system.


## 👤 Author
**Amir Tavassoli**
MSc Software Engineering | University of Bolton

## 📄 License
This is an educational project created for portfolio and assessment purposes.