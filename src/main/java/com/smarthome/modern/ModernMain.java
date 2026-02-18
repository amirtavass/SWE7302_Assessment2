package com.smarthome.modern;

import com.smarthome.modern.factory.DeviceFactory;
import com.smarthome.modern.factory.LightFactory;
import com.smarthome.modern.factory.ThermostatFactory;

public class ModernMain {
    public static void main(String[] args) {
        System.out.println("Starting Modern SmartHome System...");


        DeviceFactory lightFactory = new LightFactory();
        DeviceFactory thermostatFactory = new ThermostatFactory();


        SmartDevice light = lightFactory.createDevice();
        SmartDevice thermostat = thermostatFactory.createDevice();

        // (Polymorphism)
        light.turnOn();
        thermostat.turnOn();

        System.out.println(light.getStatus());
        System.out.println(thermostat.getStatus());


        System.out.println("\n--- Running Factory Maintenance ---");
        lightFactory.performMaintenance();
    }
}