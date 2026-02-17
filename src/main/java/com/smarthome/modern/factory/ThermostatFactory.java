package com.smarthome.modern.factory;

import com.smarthome.legacy.LegacyThermostat;
import com.smarthome.modern.SmartDevice;
import com.smarthome.modern.ThermostatAdapter;

public class ThermostatFactory extends DeviceFactory {
    @Override
    public SmartDevice createDevice() {

        return new ThermostatAdapter(new LegacyThermostat());
    }
}