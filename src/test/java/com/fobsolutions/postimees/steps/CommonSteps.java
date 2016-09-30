package com.fobsolutions.postimees.steps;

import com.fobsolutions.postimees.implementation.ActivitiesImpl;
import com.fobsolutions.postimees.utils.Constants;
import com.fobsolutions.postimees.utils.TestData;
import com.fobsolutions.postimees.utils.VideoResult;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cify.framework.core.DeviceManager;
import io.cify.framework.core.models.Device;

/**
 * Created by FOB Solutions
 */
public class CommonSteps {

    @Given("^user opens postimees main page$")
    public void userOpensPostimeesMainPage() {
        Device device = DeviceManager.createDevice();
        if (device.getCapabilityByName("url") != null) {
            device.openBrowser(device.getCapabilityByName("url"));
        } else {
            device.openBrowser(TestData.BASE_URL);
        }
        TestData.BASE_URL = device.getDriver().getCurrentUrl();

        if (ActivitiesImpl.getWelcomeScreenActivities(device).isWelcomeScreenVisible()) {
            ActivitiesImpl.getWelcomeScreenActivities(device).clickWelcome();
        }
    }

    @Then("^all found videos should have valid videos$")
    public void allFoundVideosShouldHaveCorrectVideos() {
        System.out.println(Constants.ANSI_GREEN + "---------------RESULTS---------------" + Constants.ANSI_GREEN);
        System.lineSeparator();
        System.out.println(Constants.ANSI_GREEN + "Found " + TestData.getTotalWithVideos() + " articles with videos" + Constants.ANSI_GREEN);
        System.lineSeparator();

        TestData.getVideoResults().stream().filter(result -> result.getResult() == VideoResult.Result.FAIL).forEach(result -> {
            throw new AssertionError("All videos should be valid and playable");
        });
    }

    @After
    public void closeDrivers() {
        for (VideoResult result : TestData.getVideoResults()) {
            if (result.getResult() == VideoResult.Result.FAIL) {
                System.out.println(Constants.ANSI_RED + result.toString() + Constants.ANSI_RED);
            } else {
                System.out.println(Constants.ANSI_GREEN + result.toString() + Constants.ANSI_GREEN);
            }
            System.lineSeparator();
        }
        DeviceManager.quitDevices();
    }
}
