package com.smarthome.modern;

public class Thermostat {
    private int temperature;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Thermostat set to " +temp+ "°F");

    }
    public int getTemperature() {
        return temperature;
    }
}
