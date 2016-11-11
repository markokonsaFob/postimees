package com.fobsolutions.postimees.utils;

import com.saucelabs.saucerest.SauceREST;
import io.cify.framework.core.Device;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by FOB Solutions
 */
public class TestHelper {


    private static String SAUCELABS_USERNAME = System.getenv("SAUCELABS_USER");
    private static String SAUCELABS_ACCESSKEY = System.getenv("SAUCELABS_ACCESSKEY");

    /**
     * Returns SauceLabs public link
     */
    public static String getPublicJobLink(Device device) {

        String link = "";
        try {
            if (device.getDriver() instanceof RemoteWebDriver) {
                link = getRestApi().getPublicJobLink(((RemoteWebDriver) device.getDriver()).getSessionId().toString());
            }
        } catch (Exception e) {
            System.out.print("Cannot connect to SauceLabs");
        }
        return link;
    }

    /**
     * Gets sauce rest api
     */
    private static SauceREST getRestApi() {
        return new SauceREST(SAUCELABS_USERNAME, SAUCELABS_ACCESSKEY);
    }
}
