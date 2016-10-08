package com.fobsolutions.postimees.utils;


import io.cify.framework.core.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class Advertisement {

    private static By supriseFull = By.id("surprise-full");

    public static boolean isAdDisplayed(Device device) {
        try {
            return device.getDriver().findElement(supriseFull).isDisplayed();
        } catch (NoSuchElementException nse) {
            return false;
        }
    }
}

