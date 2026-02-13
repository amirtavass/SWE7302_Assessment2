package com.smarthome.legacy;

public class SmartLight {
    private boolean isOn;

    public void turnOn() {
        this.isOn = true;
        System.out.println("Smart Light turned ON.");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println("Smart Light turned OFF.");
    }
}
