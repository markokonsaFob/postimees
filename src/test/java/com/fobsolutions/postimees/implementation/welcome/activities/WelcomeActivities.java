package com.fobsolutions.postimees.implementation.welcome.activities;

import com.fobsolutions.postimees.implementation.welcome.WelcomePage;
import io.cify.framework.core.Device;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created by FOB Solutions
 */
public class WelcomeActivities implements IWelcomeActivities {

    private WelcomePage page;

    WelcomeActivities(Device device) {
        this.page = new WelcomePage(device);
    }

    /**
     * Check if welcome screen is visible
     */
    @Override
    public boolean isWelcomeScreenVisible() {

        boolean isDisplayed;

        try {
            isDisplayed = page.getWelcomeScreen().isDisplayed();
        } catch (NoSuchElementException ignored) {
            isDisplayed = false;
        }

        return isDisplayed;
    }

    /**
     * Clicks welcome button
     */
    @Override
    public void clickWelcome() {
        page.getWelcomeBtn().click();
    }
}
