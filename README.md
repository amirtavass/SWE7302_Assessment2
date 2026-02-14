# SmartHome Manager 🏠

**Assessment 2: Advanced Software Development (SWE7302)**

## 📋 Project Overview
This repository contains the codebase for the **SmartHome Manager**, a legacy system designed to control household IoT devices.

Currently, the system operates as a functional prototype but exhibits high coupling and low cohesion. The purpose of this project is to serve as a **refactoring case study**. Over the course of development, this codebase will evolve from its current monolithic state into a modular, scalable architecture using industry-standard best practices.

> **⚠️ Note:** The initial code in the `legacy` package contains intentional design flaws (violation of SOLID principles, hardcoded dependencies, etc.) to demonstrate the necessity and impact of proper software engineering techniques.

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
    ```bash
    mvn exec:java -Dexec.mainClass="com.smarthome.legacy.Main"
    ```

## 🛠 Project Goals
This repository tracks the journey of modernizing a legacy application. Key objectives include:
* Identifying and remediating code smells.
* Decoupling high-level policy from low-level implementation.
* Demonstrating the practical application of Object-Oriented Analysis and Design (OOAD).
* Integrating design patterns to solve specific architectural problems.

## 📚 References
The refactoring strategies applied in this project are based on concepts from:
* *Clean Code* by Robert C. Martin
* *Design Patterns: Elements of Reusable Object-Oriented Software* by Gamma et al.
* *Refactoring* by Martin Fowler

## 👤 Author
**Amir Tavassoli**
MSc Software Engineering | University of Bolton

## 📄 License
This is an educational project created for portfolio and assessment purposes.