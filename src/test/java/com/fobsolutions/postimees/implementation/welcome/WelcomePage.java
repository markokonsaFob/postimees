package com.fobsolutions.postimees.implementation.welcome;

import io.cify.framework.PageObjects;
import io.cify.framework.core.models.Device;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by FOB Solutions
 */
public class WelcomePage extends PageObjects {

    public WelcomePage(Device device) {
        super(device, 0);
    }

    @FindBy(id = "welcome-screen")
    private WebElement welcomeScreen;

    @FindBy(className = "welcome-screen__button")
    private WebElement welcomeBtn;

    public WebElement getWelcomeScreen() {
        return welcomeScreen;
    }

    public WebElement getWelcomeBtn() {
        return welcomeBtn;
    }
}
