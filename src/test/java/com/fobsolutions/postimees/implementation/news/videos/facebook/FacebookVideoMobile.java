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
        device.getDriver().switchTo().defaultContent();
        device.getDriver().switchTo().frame(getFrame());
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
            device.getDriver().findElement(root).findElement(playBtnMobile).click();
            device.getDriver().findElement(video).click();
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
            return device.getDriver().findElement(root);
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
