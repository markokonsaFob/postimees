package com.fobsolutions.postimees.implementation.news.videos.leadmedia;

import com.fobsolutions.postimees.implementation.news.videos.VideoComponent;
import io.appium.java_client.ios.IOSDriver;
import io.cify.framework.core.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.fobsolutions.postimees.implementation.news.videos.VideoUtil.waitForStateToBe;
import static com.fobsolutions.postimees.implementation.news.videos.VideoUtil.waitForVideoContainer;
import static com.fobsolutions.postimees.utils.BrowserActions.click;

/**
 * Created by FOB Solutions
 */
public class LeadMediaVideoMobile implements VideoComponent {


    @FindBy(className = "video-wrapper")
    private WebElement leadMedia;

    private By jwPlayer = By.id("video-element");

    private Device device;

    public LeadMediaVideoMobile(Device device) {
        PageFactory.initElements(device.getDriver(), this);
        this.device = device;
    }

    /**
     * Checks if component have video
     */
    @Override
    public boolean haveVideo() {

        return leadMedia != null && leadMedia.isDisplayed();
    }

    /**
     * Gets player state
     */
    @Override
    public VideoState getState() {
        WebElement player;
        player = device.getDriver().findElement(jwPlayer);


        if (player == null) {
            return VideoState.NOTAVAILABLE;
        } else if (player.getAttribute("class").contains("jw-state-idle")) {
            return VideoState.IDLE;
        } else if (player.getAttribute("class").contains("jw-state-paused")) {
            return VideoState.PAUSED;
        } else if (player.getAttribute("class").contains("jw-state-playing")) {
            return VideoState.PLAYING;
        } else if (player.getAttribute("class").contains("jw-state-buffering")) {
            return VideoState.BUFFERING;
        } else if (player.getAttribute("class").contains("jw-state-error")) {
            return VideoState.ERROR;
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
            try {
                if (device.getDriver() instanceof IOSDriver) {
                    click(device, device.getDriver().findElement(jwPlayer));
                } else {
                    device.getDriver().findElement(jwPlayer).click();
                }
            } catch (Exception ignored) {
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
            device.getDriver().switchTo().defaultContent();
            click(device, leadMedia);
            waitForVideoContainer(device, By.className("video-container"), 10);
            return device.getDriver().findElement(jwPlayer);
        } catch (WebDriverException ignored) {
            return null;
        }
    }
}
