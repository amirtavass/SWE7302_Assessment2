package com.smarthome.modern;

public class SecuritySystem {
    private boolean armed;


public void arm() {
    this.armed = true;
    System.out.println("Security System armed.");
}
public void disarm() {
    this.armed = false;
    System.out.println("Security System DISARMED");
}

public boolean isArmed() {
    return armed;
}
}
