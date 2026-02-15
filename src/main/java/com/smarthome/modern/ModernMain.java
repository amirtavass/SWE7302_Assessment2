package com.smarthome.modern;

import com.smarthome.legacy.LegacyThermostat;

public class ModernMain {
    public static void main(String[] args) {
        System.out.println("Starting Modern SmartHome System...");


        SmartDevice light = new ModernLight();
        SmartDevice thermostat = new ThermostatAdapter(new LegacyThermostat());

        // showing polymorphism
        light.turnOn();
        thermostat.turnOn();

        System.out.println(light.getStatus());
        System.out.println(thermostat.getStatus());


        if (thermostat instanceof ThermostatAdapter) {
            ((ThermostatAdapter) thermostat).setTemperature(22.0);
        }

        System.out.println(thermostat.getStatus());
    }
}