package com.fobsolutions.postimees.implementation.news.videos.instagram;

import com.fobsolutions.postimees.implementation.news.videos.VideoComponent;
import com.fobsolutions.postimees.utils.BrowserActions;
import io.appium.java_client.ios.IOSDriver;
import io.cify.framework.core.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static com.fobsolutions.postimees.implementation.news.videos.VideoUtil.waitForStateToBe;

/**
 * Created by FOB Solutions
 */
public class InstagramVideo implements VideoComponent {

    private By videoContainer = By.className("EmbedVideo");
    private By instagramMedia = By.className("instagram-media");
    private Device device;

    public InstagramVideo(Device device) {
        this.device = device;
    }

    /**
     * Checks if component have video
     */
    @Override
    public boolean haveVideo() {
        return getPlayer() != null;
    }

    /**
     * Gets player state
     */
    @Override
    public VideoState getState() {
        if (getPlayer() != null) {
            if (getPlayer().findElement(By.tagName("video")).getAttribute("loop") == null) {
                return VideoState.IDLE;
            } else {
                return VideoState.PLAYING;
            }
        } else {
            return VideoState.NOTAVAILABLE;
        }
    }

    /**
     * Play video
     */
    @Override
    public void play() {
        if (getPlayer() != null) {
            if (device.getDriver() instanceof IOSDriver) {
                BrowserActions.click(device, getPlayer());
            } else {
                getPlayer().click();
            }
            waitForStateToBe(this, device, VideoState.PLAYING, 30);
        }
    }

    /**
     * Gets player
     */
    @Override
    public WebElement getPlayer() {
        try {
            if (getPlayerElement() == null) {
                device.getDriver().switchTo().defaultContent();
                if (device.getDriver() instanceof IOSDriver) {
                    device.getDriver().get(device.getDriver().findElement(instagramMedia).getAttribute("src"));
                } else {
                    switchToInstagram();
                }
            }
            return getPlayerElement();
        } catch (NoSuchElementException ignored) {
            return null;
        }
    }

    /**
     * Switches to Instagram frame
     */
    private void switchToInstagram() {
        try {
            device.getDriver().switchTo().frame("instagram-embed-0");
        } catch (Exception ignored) {
        }
    }

    /**
     * Returns player element
     */
    private WebElement getPlayerElement() {
        try {
            return device.getDriver().findElement(videoContainer);
        } catch (NoSuchElementException ignored) {
            return null;
        }
    }
}
