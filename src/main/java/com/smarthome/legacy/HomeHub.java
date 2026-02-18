package com.smarthome.legacy;

public class HomeHub {
    public SmartLight livingRoomLight;
    public LegacyThermostat oldThermostat;

    public HomeHub() {
        this.livingRoomLight = new SmartLight();
        this.oldThermostat = new LegacyThermostat();
    }

    public void runSystem(String mode) {
        System.out.println("--- System Running in " + mode + " Mode ---");


        if (mode.equals("Eco")) {
            System.out.println("Optimizing for low power...");
            livingRoomLight.turnOff();
            oldThermostat.setTempFahrenheit(60.0);

            // Hardcoded energy logic
            if (oldThermostat.getTempFahrenheit() > 65) {
                System.out.println("Warning: Temp too high for Eco!");
            }
        }
        else if (mode.equals("Party")) {
            System.out.println("Preparing environment for guests...");
            livingRoomLight.turnOn();
            oldThermostat.setTempFahrenheit(72.0);
            System.out.println("Music System (Hardcoded) turned ON");
        }
        else if (mode.equals("Winter")) {
            System.out.println("Heating up the house...");
            livingRoomLight.turnOn();
            oldThermostat.setTempFahrenheit(80.0);
            System.out.println("Defrost cycle started.");
        }
        else {
            System.out.println("Unknown mode. System idle.");
        }

        System.out.println("-----------------------------------");
    }
}