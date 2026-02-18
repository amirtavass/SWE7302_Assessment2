package com.smarthome.legacy;

public class HomeHub {

    public SmartLight livingRoomLight;
    public LegacyThermostat oldThermostat;

    public HomeHub() {
        this.livingRoomLight = new SmartLight();
        this.oldThermostat = new LegacyThermostat();
    }


    public void runSystem(String mode) {
        System.out.println("Running system in mode: " + mode);

        if (mode.equals("Eco")) {
            // Hardcoded logic for Eco mode
            livingRoomLight.turnOff();
            oldThermostat.setTempFahrenheit(60.0); // We have to know it uses F!
        } else if (mode.equals("Party")) {
            // Hardcoded logic for Party mode
            livingRoomLight.turnOn();
            oldThermostat.setTempFahrenheit(72.0);
        } else {
            System.out.println("Unknown mode.");
        }
    }
}
