package com.smarthome.modern.factory;

import com.smarthome.modern.ModernLight;
import com.smarthome.modern.SmartDevice;

public class LightFactory extends DeviceFactory {
    @Override
    public SmartDevice createDevice() {
        // Encapsulates the logic of creating a light
        return new ModernLight();
    }
}