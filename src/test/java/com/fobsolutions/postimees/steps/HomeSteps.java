package com.fobsolutions.postimees.steps;

import com.fobsolutions.postimees.implementation.ActivitiesImpl;
import com.fobsolutions.postimees.utils.TestData;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cify.framework.core.Device;
import io.cify.framework.core.DeviceManager;

import static org.junit.Assert.assertTrue;

/**
 * Created by FOB Solutions
 */
public class HomeSteps {

    @When("^user collects (\\d+) first articles")
    public void userCollectsFirstVideos(int nth) {
        Device device = DeviceManager.getInstance().getActiveDevice();
        ActivitiesImpl.getHomeActivities(device).collectArticles(nth);
    }

    @Then("^should have (\\d+) articles with URL's$")
    public void shouldHaveArticlesWithURLS(int nth) {
        assertTrue(TestData.getCollectedUrls().size() == nth);
    }
}
