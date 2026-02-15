package com.smarthome.modern;

import com.smarthome.legacy.LegacyThermostat;

public class ThermostatAdapter implements SmartDevice {
    private final LegacyThermostat legacyThermostat;
    private boolean isActive;

    public ThermostatAdapter(LegacyThermostat legacyThermostat) {
        this.legacyThermostat = legacyThermostat;
    }

    @Override
    public void turnOn() {
        this.isActive = true;
        // Default behavior:room temp (21°C -> ~70°F) when turned on
        double defaultF = (21 * 9.0/5.0) + 32;
        legacyThermostat.setTempFahrenheit(defaultF);
        System.out.println("Adapter: Legacy Thermostat activated via Adapter.");
    }

    @Override
    public void turnOff() {
        this.isActive = false;
        System.out.println("Adapter: Legacy Thermostat switched off.");
    }

    @Override
    public String getStatus() {
        double tempF = legacyThermostat.getTempFahrenheit();
        double tempC = (tempF - 32) * 5.0/9.0;
        return String.format("Thermostat is %s. Temp: %.1f°C",
                (isActive ? "ON" : "OFF"), tempC);
    }


    public void setTemperature(double celsius) {
        if (!isActive) {
            System.out.println("Adapter: Cannot set temp, device is OFF.");
            return;
        }
        double fahrenheit = (celsius * 9.0/5.0) + 32;
        legacyThermostat.setTempFahrenheit(fahrenheit);
    }
}