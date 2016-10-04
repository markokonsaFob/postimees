package com.fobsolutions.postimees.implementation.news.videos.leadmedia;

import com.fobsolutions.postimees.implementation.news.videos.VideoComponent;
import io.cify.framework.core.Device;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.fobsolutions.postimees.implementation.news.videos.VideoUtil.waitForStateToBe;

/**
 * Created by FOB Solutions
 */
public class LeadMediaVideoDesktop implements VideoComponent {

    @FindAll({
            @FindBy(className = "lead-media"),
            @FindBy(className = "articleVideoWrapper")
    })
    private WebElement leadMedia;

    private By jwPlayer = By.className("jwplayer");

    private Device device;

    public LeadMediaVideoDesktop(Device device) {
        PageFactory.initElements(device.getDriver(), this);
        this.device = device;
    }

    /**
     * Checks if component have video
     */
    @Override
    public boolean haveVideo() {

        return getPlayer() != null && getPlayer().isDisplayed();
    }

    /**
     * Gets player state
     */
    @Override
    public VideoState getState() {

        WebElement player = getPlayer();

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
            getPlayer().sendKeys(Keys.ENTER);
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
            return leadMedia.findElement(jwPlayer);
        } catch (NoSuchElementException ignored) {
            return null;
        }
    }
}
