# SmartHome Manager 🏠 v2.1

**Assessment 2: Advanced Software Development (SWE7302)**

## 📋 Project Overview
SmartHome Manager is an educational project that demonstrates how to refactor poorly designed legacy code into a modern, maintainable architecture using design patterns and SOLID principles. It serves as a "before and after" showcase of software evolution.

## 🚀 How to Run

### Prerequisites
* **Java Development Kit (JDK):** Version 25 or higher
* **Build Tool:** Maven

### Installation & Execution

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/amirtavass/SWE7302_Assessment2.git](https://github.com/amirtavass/SWE7302_Assessment2.git)
    cd SmartHomeManager
    ```

2.  **Build the project**
    ```bash
    mvn clean install
    ```

3.  **Run the application**
    * **Option A: Legacy System (The "Before" State)**
        ```bash
        mvn exec:java -Dexec.mainClass="com.smarthome.legacy.Main"
        ```
    * **Option B: Modern System (The "After" State)**
        ```bash
        mvn exec:java -Dexec.mainClass="com.smarthome.modern.ModernMain"
        ```

## 🏗 Refactoring Progress & Patterns
This section tracks the modernization of the codebase.

### ✅ Phase 1: Standardization & Adapter Pattern (Completed)
* **Goal:** Decouple the system from concrete legacy classes and fix interface incompatibility.
* **Changes:**
    * **Abstraction:** Introduced the `SmartDevice` interface to standardize behaviors (`turnOn`, `turnOff`, `getStatus`) across all devices.
    * **Structural Pattern (Adapter):** Implemented `ThermostatAdapter` to wrap the incompatible `LegacyThermostat`. This allows the modern system to control legacy hardware (which uses Fahrenheit) using standard Celsius inputs.

### ✅ Phase 2: Object Creation & Factory Pattern (Completed)
* **Goal:** Remove rigid dependencies (`new` keyword) from the client logic to improve scalability.
* **Changes:**
    * **Creational Pattern (Factory Method):** Implemented a factory layer (`DeviceFactory`, `LightFactory`, `ThermostatFactory`) to encapsulate device instantiation logic.
    * **Encapsulation:** The client code (`ModernMain`) no longer knows about the complex setup required for legacy adapters.

### 🚧 Phase 3: Logic Expansion & Behavioral Patterns (In Progress)
* **Context:** The legacy `HomeHub` logic has been expanded to simulate a "God Class" problem. It currently relies on complex, monolithic `if/else` chains to manage system modes (e.g., "Eco", "Party", "Winter").
* **Goal:** Refactor the hardcoded control logic into a flexible, plugin-based system.
* **Planned Changes:**
    * **Behavioral Pattern (Strategy):** Implement the **Strategy Pattern** to encapsulate energy algorithms into separate classes (`EcoStrategy`, `PartyStrategy`). This will allow the system to switch modes dynamically without modifying the core hub logic, satisfying the **Single Responsibility Principle**.

## 👤 Author
**Amir Tavassoli**
MSc Software Engineering | University of Bolton

## 📄 License
This is an educational project created for portfolio and assessment purposes.