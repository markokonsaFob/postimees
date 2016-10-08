package com.fobsolutions.postimees.implementation.news.videos.facebook;

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
public class FacebookVideoMobile implements VideoComponent {

    private By iframeWidget = By.className("fb_iframe_widget");
    private By video = By.tagName("video");
    private By root = By.id("root");
    private By iframe = By.tagName("iframe");
    private By playBtnMobile = By.cssSelector("div[data-sigil='m-video-play-button playInlineVideo']");
    private Device device;

    public FacebookVideoMobile(Device device) {
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
        boolean playing = Integer.valueOf(device.getDriver().findElement(video).getAttribute("width")) > 0;

        if (playing) {
            return VideoState.PLAYING;
        } else {
            return VideoState.IDLE;
        }
    }

    /**
     * Play video
     */
    @Override
    public void play() {
        try {
            if (device.getDriver() instanceof IOSDriver) {

                BrowserActions.click(device, device.getDriver().findElement(root).findElement(playBtnMobile));
                BrowserActions.click(device, device.getDriver().findElement(video));
            } else {
                device.getDriver().findElement(root).findElement(playBtnMobile).click();
                device.getDriver().findElement(video).click();
            }
        } catch (Exception ignored) {
        }
        waitForStateToBe(this, device, VideoState.PLAYING, 30);
    }

    /**
     * Gets player
     */
    @Override
    public WebElement getPlayer() {
        try {
            if (getPlayerElement() == null) {
                if (device.getDriver() instanceof IOSDriver) {
                    device.getDriver().get(getFrame().getAttribute("src"));
                } else {
                    device.getDriver().switchTo().defaultContent();
                    BrowserActions.scrollToElement(device, getFrame());
                    device.getDriver().switchTo().frame(getFrame());
                }
            }
            return device.getDriver().findElement(root);
        } catch (NoSuchElementException ignored) {
            return null;
        }
    }

    /**
     * Gets frame
     */
    private WebElement getFrame() {
        device.getDriver().switchTo().defaultContent();
        return device.getDriver().findElement(iframeWidget).findElement(iframe);
    }

    /**
     * Returns player element
     */
    private WebElement getPlayerElement() {
        try {
            return device.getDriver().findElement(root);
        } catch (Exception ignored) {
            return null;
        }
    }
}
