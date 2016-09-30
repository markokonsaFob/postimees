package com.fobsolutions.postimees.implementation.home.activities;

import java.util.List;

/**
 * Created by FOB Solutions
 */
public interface IHomeActivities {

    /**
     * Collect articles
     *
     * @param nth - how many articles to collect
     */
    List<String> collectArticles(int nth);

    /**
     * Open article with URL
     *
     * @param url - article URL
     */
    void openArticle(String url);

}
