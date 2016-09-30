package com.fobsolutions.postimees.implementation.news.videos.facebook;

import com.fobsolutions.postimees.implementation.news.videos.VideoComponent;
import io.cify.framework.core.models.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static com.fobsolutions.postimees.implementation.news.videos.VideoUtil.waitForStateToBe;

/**
 * Created by FOB Solutions
 */
public class FacebookVideoDesktop implements VideoComponent {

    private By iframeWidget = By.className("fb_iframe_widget");
    private By facebook = By.id("facebook");
    private By video = By.tagName("video");
    private By iframe = By.tagName("iframe");
    private By playBtn = By.cssSelector("input[aria-label='Play video']");
    private Device device;

    public FacebookVideoDesktop(Device device) {
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
        device.getDriver().switchTo().defaultContent();
        device.getDriver().switchTo().frame(getFrame());
        String classString = device.getDriver().findElement(playBtn).getAttribute("class");

        if (classString.contains("hidden_elem")) {
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
            device.getDriver().findElement(playBtn).click();
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
            device.getDriver().switchTo().defaultContent();
            device.getDriver().switchTo().frame(getFrame());
            return device.getDriver().findElement(facebook).findElement(video);
        } catch (NoSuchElementException ignored) {
            return null;
        }
    }

    /**
     * Gets frame
     */
    private WebElement getFrame() {
        return device.getDriver().findElement(iframeWidget).findElement(iframe);
    }
}
