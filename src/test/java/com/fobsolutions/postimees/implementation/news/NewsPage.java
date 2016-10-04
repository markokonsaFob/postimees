package com.fobsolutions.postimees.implementation.news;

import com.fobsolutions.postimees.implementation.news.videos.VideoComponent;
import com.fobsolutions.postimees.implementation.news.videos.facebook.FacebookVideoDesktop;
import com.fobsolutions.postimees.implementation.news.videos.facebook.FacebookVideoMobile;
import com.fobsolutions.postimees.implementation.news.videos.inarticle.InArticleVideoDesktop;
import com.fobsolutions.postimees.implementation.news.videos.inarticle.InArticleVideoMobile;
import com.fobsolutions.postimees.implementation.news.videos.instagram.InstagramVideo;
import com.fobsolutions.postimees.implementation.news.videos.leadmedia.LeadMediaVideoDesktop;
import com.fobsolutions.postimees.implementation.news.videos.leadmedia.LeadMediaVideoMobile;
import com.fobsolutions.postimees.implementation.news.videos.youtube.YouTubeVideo;
import com.fobsolutions.postimees.utils.Constants;
import com.fobsolutions.postimees.utils.TestData;
import com.fobsolutions.postimees.utils.VideoResult;
import io.cify.framework.PageObjects;
import io.cify.framework.core.Device;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FOB Solution
 */
public class NewsPage extends PageObjects {

    private List<VideoComponent> componentList = new ArrayList<>();
    private Device device;

    public NewsPage(Device device) {
        super(device, 0);
        this.device = device;

        componentList.addAll(Arrays.asList(
                new InArticleVideoDesktop(device),
                new InArticleVideoMobile(device),
                new LeadMediaVideoDesktop(device),
                new LeadMediaVideoMobile(device),
                new FacebookVideoDesktop(device),
                new FacebookVideoMobile(device),
                new YouTubeVideo(device),
                new InstagramVideo(device)
        ));
    }

    /**
     * Returns video component class
     */
    public VideoComponent getVideo() {
        for (VideoComponent component : componentList) {
            try {
                if (component.haveVideo()) {
                    System.out.println(Constants.ANSI_YELLOW + "Found video with type " + component.getClass().getSimpleName() + Constants.ANSI_YELLOW);
                    return component;
                }
            } catch (NoSuchElementException ignored) {
            }
        }
        throw new NotFoundException("Didn't found video!");
    }

    /**
     * Checks if video is present
     */
    public boolean haveVideo() {
        for (VideoComponent component : componentList) {
            try {
                if (component.haveVideo()) {
                    return true;
                }
            } catch (NoSuchElementException ignored) {
            }
        }
        return false;
    }

    /**
     * Checks for video on url
     *
     * @param url - article url
     */
    public void checkForVideo(String url) {
        System.out.println(Constants.ANSI_BLACK + "-------------------------------------" + Constants.ANSI_BLACK);
        try {
            System.out.println(Constants.ANSI_GREEN + "Opening article with URL " + url + Constants.ANSI_GREEN);
            device.getDriver().get(url);
            VideoComponent component = getVideo();
            playVideo(component);
            addResult(url, component);
        } catch (Exception ignored) {
            System.out.println(Constants.ANSI_YELLOW + "Did not find video on that article" + Constants.ANSI_YELLOW);
        }
        System.out.println(Constants.ANSI_BLACK + "-------------------------------------" + Constants.ANSI_BLACK);
    }

    /**
     * Press play and wait for correct state
     *
     * @param component - VideoComponent type
     */
    public boolean playVideo(VideoComponent component) {
        try {
            component.play();
            System.out.println(Constants.ANSI_GREEN + "Video started correctly" + Constants.ANSI_GREEN);
            return true;
        } catch (TimeoutException ignored) {
            System.out.println(Constants.ANSI_RED + "Video did not start" + Constants.ANSI_RED);
            return false;
        }
    }

    /**
     * Add result to global data
     *
     * @param component video component type
     * @param url       article url
     */
    private void addResult(String url, VideoComponent component) {
        TestData.addVideoResult(new VideoResult(
                url,
                component.getState() == VideoComponent.VideoState.PLAYING,
                "Video should be PLAYING but is " + component.getState(),
                component.getClass().getSimpleName(),
                device)
        );
        TestData.addTotalWithVideos();
    }

}
