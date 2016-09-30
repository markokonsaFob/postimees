package com.fobsolutions.postimees.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FOB Solutions
 */
public class TestData {

    public static String BASE_URL = System.getProperty("url", "http://www.postimees.ee/v2");

    private static ThreadLocal<List<String>> collectedUrls = new ThreadLocal<>();
    private static int totalWithVideos = 0;
    private static List<VideoResult> videoResults = new ArrayList<>();

    /**
     * Gets collected URLs for current thread
     */
    public static List<String> getCollectedUrls() {
        if (TestData.collectedUrls.get() == null) {
            TestData.collectedUrls.set(new ArrayList<>());
        }
        return collectedUrls.get();
    }

    /**
     * Sets collected URLs for current thread
     */
    public static void setCollectedUrls(List<String> collectedUrls) {
        if (TestData.collectedUrls.get() == null) {
            TestData.collectedUrls.set(new ArrayList<>());
        }
        TestData.collectedUrls.set(collectedUrls);
    }

    /**
     * Total articles with videos found
     */
    public static int getTotalWithVideos() {
        return totalWithVideos;
    }

    /**
     * Sets total amount
     */
    public static void addTotalWithVideos() {
        TestData.totalWithVideos++;
    }

    /**
     * Add video result
     */
    public static void addVideoResult(VideoResult result) {
        TestData.videoResults.add(result);
    }

    /**
     * Get video results
     */
    public static List<VideoResult> getVideoResults() {
        return TestData.videoResults;
    }

    /**
     * Example URL's
     */
    public static List<String> getMockedUrls() {
        return Arrays.asList(
                "http://sport.postimees.ee/3611563/jalgpalli-otsepilt-kaotusseisust-vaelja-tulnud-infonet-voitis-nomme-kaljut",
                "http://video.postimees.ee/3824985/postimehe-augustikuu-veebivideod",
                "http://sport.postimees.ee/3611787/video-lillardi-41-punkti-peatasid-portlandi-kaotuseseeria",
                "http://sport.postimees.ee/3608777/video-kelly-sildaru-harjutab-lumelauasoitu",
                "http://elu24.postimees.ee/3818671/video-steve-o-murdis-peldikutrikki-tehes-molemad-jalad");
    }

    public static List<String> getMobileMocked() {
        return Arrays.asList(
                "http://m.postimees.ee/section/2277/3611563",
                "http://m.postimees.ee/section/2277/3824985",
                "http://m.postimees.ee/section/2277/3611787",
                "http://m.postimees.ee/section/2277/3608777",
                "http://m.postimees.ee/section/2277/3818671"
        );
    }
}
