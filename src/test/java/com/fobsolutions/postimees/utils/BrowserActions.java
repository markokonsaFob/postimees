package com.fobsolutions.postimees.utils;

import io.appium.java_client.ios.IOSDriver;
import io.cify.framework.core.Device;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * Created by FOB Solutions
 * <p>
 * Basic driver actions
 */
public class BrowserActions {

    /**
     * Abstract click for mobile and desktop devices
     */
    public static void click(Device device, WebElement element) {
        if (device.getDriver() instanceof IOSDriver) {
            tap(device, element);
        } else {
            element.click();
        }
    }

    /**
     * Scrolls to element with javascript
     */
    public static void scrollToElement(Device device, WebElement element) {
        ((JavascriptExecutor) device.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * IOS Click
     */
    private static void tap(Device device, WebElement we) {
        IOSDriver iosDriver = ((IOSDriver) device.getDriver());
        String originalContext = iosDriver.getContext();
        Point p = we.getLocation();
        Dimension size = we.getSize();
        try {
            iosDriver.context("NATIVE_APP");
            Point appLoc = TestData.getApplicationSize(device);
            iosDriver.tap(1, appLoc.x + p.x + (size.width / 2), appLoc.y + p.y + (size.height / 2), 1);
        } finally {
            iosDriver.context(originalContext);
        }
    }
}
