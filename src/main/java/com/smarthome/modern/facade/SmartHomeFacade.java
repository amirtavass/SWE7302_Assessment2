package com.smarthome.modern.facade;

import com.smarthome.modern.SmartDevice;
import com.smarthome.modern.Thermostat;
import com.smarthome.modern.SecuritySystem;
import com.smarthome.modern.factory.LightFactory;
import com.smarthome.modern.factory.ThermostatFactory;

public class SmartHomeFacade {
    private SmartDevice light;
    private Thermostat thermostat;
    private SecuritySystem securitySystem;

    public SmartHomeFacade() {

        this.light = new LightFactory().createDevice();
        this.thermostat = new Thermostat();
        this.securitySystem = new SecuritySystem();
    }

    public void activateNightMode() {
        System.out.println("\n🌙 Activating Night Mode...");
        light.turnOff();
        thermostat.setTemperature(68);
        securitySystem.arm();
        System.out.println("Night Mode activated!\n");
    }

    public void deactivateNightMode() {
        System.out.println("\n☀️ Deactivating Night Mode...");
        light.turnOn();
        thermostat.setTemperature(72);
        securitySystem.disarm();
        System.out.println("Night Mode deactivated!\n");
    }
}