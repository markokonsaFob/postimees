package com.fobsolutions.postimees.implementation.news.videos.youtube;

import com.fobsolutions.postimees.implementation.news.videos.VideoComponent;
import io.cify.framework.core.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static com.fobsolutions.postimees.implementation.news.videos.VideoUtil.waitForStateToBe;

/**
 * Created by FOB Solutions
 */
public class YouTubeVideo implements VideoComponent {

    private By player = By.className("html5-video-player");
    private Device device;

    public YouTubeVideo(Device device) {
        this.device = device;
    }

    /**
     * Checks if component have video
     */
    @Override
    public boolean haveVideo() {
        try {
            return getPlayer().isDisplayed();
        } catch (Exception ignored) {
            device.getDriver().switchTo().defaultContent();
            return false;
        }
    }

    /**
     * Gets player state
     */
    @Override
    public VideoState getState() {
        if (getPlayer() != null) {
            String classString = getPlayer().getAttribute("class");
            if (classString.contains("paused-mode")) {
                return VideoState.PAUSED;
            } else if (classString.contains("playing-mode")) {
                return VideoState.PLAYING;
            } else if (classString.contains("unstarted-mode")) {
                return VideoState.IDLE;
            } else if (classString.contains("buffering-mode")) {
                return VideoState.BUFFERING;
            } else {
                return VideoState.NOTAVAILABLE;
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
        getPlayer().click();
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
            return device.getDriver().findElement(player);
        } catch (NoSuchElementException ignored) {
            return null;
        }
    }

    /**
     * Get youtube frame
     */
    private WebElement getFrame() {
        return device.getDriver().findElement(By.xpath("//iframe[starts-with(@src, 'http://www.youtube.com/embed')]"));
    }
}
