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

### 🚧 Phase 2: Dynamic Add-ons & Decorator Pattern (In Progress)
* **Context:** The `addExtra` method relies on fragile string comparisons to add 3D glasses or popcorn. Adding new snacks risks a "class explosion" or massive conditional blocks.
* **Goal:** Implement a **Structural Pattern (Decorator)**. Create a base `ITicket` interface and wrap it dynamically with `PopcornDecorator` or `GlassesDecorator` to calculate prices seamlessly.

### ⏳ Phase 3: Pricing Rules & Strategy Pattern (Planned)
* **Context:** The `checkout` method contains hardcoded math for Student and Tuesday discounts, violating the Single Responsibility Principle.
* **Goal:** Implement a **Behavioral Pattern (Strategy)**. Extract the discount math into a `PricingStrategy` interface with interchangeable classes (`StudentPricing`, `TuesdayPricing`) so new promotions can be added without modifying core logic.

## 👤 Author
**Amir Tavassoli**
MSc Software Engineering | University of Bolton

## 📄 License
This is an educational project created for portfolio and assessment purposes.