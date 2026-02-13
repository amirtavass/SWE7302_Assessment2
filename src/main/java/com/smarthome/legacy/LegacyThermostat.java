package com.smarthome.legacy;

public class LegacyThermostat {
    private double currentTempFahrenheit;

    public void setTempFahrenheit(double temp) {
        this.currentTempFahrenheit = temp;
        System.out.println("Legacy Thermostat set to " + temp + "°F");
    }

    public double getTempFahrenheit() {
        return currentTempFahrenheit;
    }
}