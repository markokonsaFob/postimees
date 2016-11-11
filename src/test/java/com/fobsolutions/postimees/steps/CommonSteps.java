package com.fobsolutions.postimees.steps;

import com.fobsolutions.postimees.implementation.ActivitiesImpl;
import com.fobsolutions.postimees.utils.Constants;
import com.fobsolutions.postimees.utils.TestData;
import com.fobsolutions.postimees.utils.TestHelper;
import com.fobsolutions.postimees.utils.VideoResult;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cify.framework.core.Device;
import io.cify.framework.core.DeviceCategory;
import io.cify.framework.core.DeviceManager;

/**
 * Created by FOB Solutions
 */
public class CommonSteps {

    @Given("^user opens postimees main page$")
    public void userOpensPostimeesMainPage() {
        Device device = DeviceManager.getInstance().createDevice(DeviceCategory.BROWSER);
        if (device.getCapabilities().getCapability("url") != null) {
            device.openBrowser(device.getCapabilities().getCapability("url").toString());
        } else {
            device.openBrowser(TestData.BASE_URL);
        }
        TestData.BASE_URL = device.getDriver().getCurrentUrl();
        TestData.setAppLocation(device);

        if (ActivitiesImpl.getWelcomeScreenActivities(device).isWelcomeScreenVisible()) {
            ActivitiesImpl.getWelcomeScreenActivities(device).clickWelcome();
        }
    }

    @Then("^all found videos should have valid videos$")
    public void allFoundVideosShouldHaveCorrectVideos() {
        System.out.println(Constants.ANSI_GREEN + "---------------RESULTS---------------" + Constants.ANSI_GREEN);
        System.lineSeparator();
        System.out.println(Constants.ANSI_BLACK + "-------------------------------------" + Constants.ANSI_BLACK);
        System.out.println(Constants.ANSI_GREEN + "Found " + TestData.getTotalWithVideos() + " articles with videos" + Constants.ANSI_GREEN);
        System.lineSeparator();
        System.out.println(Constants.ANSI_BLACK + "-------------------------------------" + Constants.ANSI_BLACK);

        TestData.getVideoResults().stream().filter(result -> result.getResult() == VideoResult.Result.FAIL).forEach(result -> {
            throw new AssertionError("All videos should be valid and playable");
        });
    }

    @After
    public void closeDrivers() {

        String sauceUrl = TestHelper.getPublicJobLink(DeviceManager.getInstance().getActiveDevice());

        if (!sauceUrl.isEmpty()) {
            System.out.println(Constants.ANSI_GREEN + "SAUCELABS URL: " + TestHelper.getPublicJobLink(DeviceManager.getInstance().getActiveDevice()) + Constants.ANSI_GREEN);
            System.lineSeparator();
            System.out.println(Constants.ANSI_BLACK + "-------------------------------------" + Constants.ANSI_BLACK);
        }

        for (VideoResult result : TestData.getVideoResults()) {
            if (result.getResult() == VideoResult.Result.FAIL) {
                System.out.println(Constants.ANSI_RED + result.toString() + Constants.ANSI_RED);
            } else {
                System.out.println(Constants.ANSI_GREEN + result.toString() + Constants.ANSI_GREEN);
            }
            System.lineSeparator();
            System.out.println(Constants.ANSI_BLACK + "-------------------------------------" + Constants.ANSI_BLACK);
        }

        DeviceManager.getInstance().quitAllDevices();
    }
}
