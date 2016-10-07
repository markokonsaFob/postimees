package com.fobsolutions.postimees.implementation.news.videos;

import io.cify.framework.core.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by FOB Solutions
 */
public class VideoUtil {

    /**
     * Waits for state to be given state
     *
     * @param state - video state
     */
    public static void waitForStateToBe(VideoComponent page, Device device, VideoComponent.VideoState state, long timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(device.getDriver(), timeOut);
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver input) {
                    return page.getState() == state;
                }
            });
        } catch (TimeoutException e) {
            throw new TimeoutException("Video state should be " + state + " but is " + page.getState());
        }
    }

    /**
     * Waits for video container (Mobile)
     */
    public static void waitForVideoContainer(Device device, By jwPlayer, long timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(device.getDriver(), timeOut);
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver input) {
                    try {
                        return device.getDriver().findElement(jwPlayer).isDisplayed();
                    } catch (NoSuchElementException e) {
                        return false;
                    }
                }
            });
        } catch (TimeoutException ignored) {
            throw new NoSuchElementException("Video container should be open within " + timeOut + " seconds on mobile devices");
        }
    }

}
