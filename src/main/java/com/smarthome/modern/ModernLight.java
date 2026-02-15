package com.smarthome.modern;

import com.smarthome.legacy.SmartLight;


public class ModernLight implements SmartDevice {
    private final SmartLight smartLight;

    public ModernLight() {
        this.smartLight = new SmartLight();
    }

    @Override
    public void turnOn() {
        smartLight.turnOn();
    }

    @Override
    public void turnOff() {
        smartLight.turnOff();
    }

    @Override
    public String getStatus() {
        return "Modern Light is working normally.";
    }
}