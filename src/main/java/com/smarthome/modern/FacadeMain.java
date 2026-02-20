package com.smarthome.modern;

import com.smarthome.modern.facade.SmartHomeFacade;

public class FacadeMain {
    public static void main(String[] args) {
        SmartHomeFacade smartHome = new SmartHomeFacade();


        smartHome.activateNightMode();


        System.out.println("... sleeping ...\n");

        smartHome.deactivateNightMode();
    }
}