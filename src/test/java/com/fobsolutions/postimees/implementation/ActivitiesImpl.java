package com.fobsolutions.postimees.implementation;

import com.fobsolutions.postimees.implementation.home.activities.IHomeActivities;
import com.fobsolutions.postimees.implementation.welcome.activities.IWelcomeActivities;
import io.cify.framework.Factory;
import io.cify.framework.core.models.Device;

/**
 * Created by FOB Solutions
 */
public class ActivitiesImpl {

    // Implementation package
    private final static String IMPL_PACKAGE = "com.fobsolutions.postimees.implementation.";

    /**
     * Gets welcome screen activities according to device type
     *
     * @param device - Device object
     */
    public static IWelcomeActivities getWelcomeScreenActivities(Device device) {
        return (IWelcomeActivities) Factory.get(device, IMPL_PACKAGE + "welcome.activities.WelcomeActivities");
    }

    /**
     * Gets home activities according to device type
     *
     * @param device - Device object
     */
    public static IHomeActivities getHomeActivities(Device device) {
        return (IHomeActivities) Factory.get(device, IMPL_PACKAGE + "home.activities.HomeActivities");
    }
}
