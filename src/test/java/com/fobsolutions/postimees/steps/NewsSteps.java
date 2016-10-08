package com.fobsolutions.postimees.steps;

import com.fobsolutions.postimees.implementation.ActivitiesImpl;
import com.fobsolutions.postimees.implementation.news.NewsPage;
import com.fobsolutions.postimees.implementation.news.videos.VideoComponent;
import com.fobsolutions.postimees.utils.TestData;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cify.framework.core.Device;
import io.cify.framework.core.DeviceCategory;
import io.cify.framework.core.DeviceManager;
import org.openqa.selenium.NotFoundException;

/**
 * Created by FOB Solutions
 */
public class NewsSteps {

    @When("^user opens each article and checks for video$")
    public void userOpensEachArticleAndChecksForVideo() {
        Device device = DeviceManager.getInstance().getActiveDevice();
        NewsPage page = new NewsPage(device);
        TestData.getCollectedUrls().forEach(page::checkForVideo);
    }

    @When("^user opens article with url \"([^\"]*)\"$")
    public void userOpensArticleWithUrl(String url) {
        Device device = DeviceManager.getInstance().createDevice(DeviceCategory.BROWSER);
        device.openBrowser(url);
        TestData.setAppLocation(device);

        if (ActivitiesImpl.getWelcomeScreenActivities(device).isWelcomeScreenVisible()) {
            ActivitiesImpl.getWelcomeScreenActivities(device).clickWelcome();
        }
    }

    @Then("^video should be visible$")
    public void videoShouldBeVisible() {
        Device device = DeviceManager.getInstance().getActiveDevice();
        NewsPage page = new NewsPage(device);
        if (!page.haveVideo()) {
            throw new NotFoundException("Video should be visible");
        }
    }

    @And("^video should be correct$")
    public void videoShouldBeCorrect() {
        Device device = DeviceManager.getInstance().getActiveDevice();
        NewsPage page = new NewsPage(device);
        VideoComponent component = page.getVideo();
        if (!page.playVideo(component)) {
            throw new AssertionError("Video should be playing");
        }
    }
}
