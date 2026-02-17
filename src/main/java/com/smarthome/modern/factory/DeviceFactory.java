package com.smarthome.modern.factory;

import com.smarthome.modern.SmartDevice;

public abstract class DeviceFactory {
    public abstract SmartDevice createDevice();

    // Optional: Shared logic for all factories can go here
    public void performMaintenance() {
        SmartDevice device = createDevice();
        device.turnOn();
        System.out.println("Factory: Maintenance check complete for " + device.getStatus());
        device.turnOff();
    }
}