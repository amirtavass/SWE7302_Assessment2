package com.smarthome.legacy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Legacy SmartHome System...");

        HomeHub hub = new HomeHub();

        // Simulating user interaction
        hub.runSystem("Eco");
        System.out.println("-------------------");
        hub.runSystem("Party");
    }
}
