package com.fobsolutions.postimees.implementation.home.activities;

import com.fobsolutions.postimees.implementation.home.HomePage;
import com.fobsolutions.postimees.utils.Constants;
import com.fobsolutions.postimees.utils.TestData;
import io.cify.framework.core.Device;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FOB Solutions
 */
public class HomeActivities implements IHomeActivities {

    private HomePage page;
    private Device device;

    public HomeActivities(Device device) {
        this.page = new HomePage(device);
        this.device = device;
    }

    /**
     * Collect articles
     *
     * @param nth - how many articles to collect
     */
    @Override
    public List<String> collectArticles(int nth) {

        device.getDriver().switchTo().defaultContent();

        System.out.println(Constants.ANSI_GREEN + "Loading articles..." + Constants.ANSI_GREEN);
        int scrolls = 0;
        while (page.getArticles().size() < nth + 20 && scrolls < nth * 2) {
            int articlesCount = page.getArticles().size();
            JavascriptExecutor jse = (JavascriptExecutor) device.getDriver();
            jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            scrolls++;

            // Print how many articles we have found already
            waitForArticlesToLoad(articlesCount);
            System.out.println(Constants.ANSI_YELLOW + "Got " + page.getArticles().size() + " articles out of " + (nth + 20) + Constants.ANSI_YELLOW);
        }

        System.out.println(Constants.ANSI_GREEN + "Loading articles finished..." + Constants.ANSI_GREEN);
        List<String> collectedUrls = new ArrayList<>();

        for (WebElement article : page.getArticles()) {
            try {
                WebElement aTag = article.findElement(By.tagName("a"));
                String url = aTag.getAttribute("href");
                if (!url.isEmpty()) {
                    collectedUrls.add(url);
                }
            } catch (NoSuchElementException ignored) {
            }
        }

        System.lineSeparator();
        System.out.println(Constants.ANSI_GREEN + "Collected " + collectedUrls.size() + " URL's" + Constants.ANSI_GREEN);
        collectedUrls = collectedUrls.subList(0, nth);
        TestData.setCollectedUrls(collectedUrls);
        return collectedUrls;
    }

    /**
     * Open article with URL
     *
     * @param url - article URL
     */
    @Override
    public void openArticle(String url) {
        device.getDriver().get(url);
    }

    /**
     * Waits for articles to load
     */
    private void waitForArticlesToLoad(int articlesCount) {
        try {
            WebDriverWait wait = new WebDriverWait(device.getDriver(), 10);
            wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver input) {
                    return articlesCount < page.getArticles().size();
                }
            });
        } catch (TimeoutException ignored) {
        }
    }
}
