package com.fobsolutions.postimees.implementation.news.videos;

import org.openqa.selenium.WebElement;

/**
 * Created by FOB Solutions
 */
public interface VideoComponent {

    /**
     * Video state
     */
    enum VideoState {
        PAUSED,
        BUFFERING,
        PLAYING,
        IDLE,
        NOTAVAILABLE,
        ERROR
    }

    /**
     * Checks if component have video
     */
    boolean haveVideo();

    /**
     * Gets player state
     */
    VideoState getState();

    /**
     * Play video
     * */
    void play();

    /**
     * Gets player
     * */
    WebElement getPlayer();

}
