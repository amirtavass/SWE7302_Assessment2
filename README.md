# SmartHome Manager 🏠

**Assessment 2: Advanced Software Development (SWE7302)**

## 📋 Project Overview
This repository contains the codebase for the **SmartHome Manager**, a system designed to control household IoT devices.

The project initially started as a legacy prototype with high coupling and low cohesion. It is currently undergoing a major refactoring process to evolve into a modular, scalable architecture. This transformation demonstrates the practical application of **Object-Oriented Analysis and Design (OOAD)** and **Design Patterns** to solve real-world architectural challenges.



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

3.  **Run the application (Legacy Version)**
    ```bash
    mvn exec:java -Dexec.mainClass="com.smarthome.legacy.Main"
    ```

## 🏗 Refactoring Progress & Patterns
This section tracks the modernization of the codebase.

### ✅ Phase 1: Standardization & Adapter Pattern
* **Goal:** Decouple the system from concrete legacy classes and fix interface incompatibility.
* **Changes:**
    * **Abstraction:** Introduced the `SmartDevice` interface to standardize behaviors (`turnOn`, `turnOff`, `getStatus`) across all devices.
    * **Structural Pattern (Adapter):** Implemented `ThermostatAdapter` to wrap the incompatible `LegacyThermostat`. This allows the modern system to control legacy hardware (which uses Fahrenheit) using standard Celsius inputs, satisfying the **Open/Closed Principle**.


## 👤 Author
**Amir Tavassoli**


## 📄 License
This is an educational project created for portfolio and assessment purposes.