package com.fobsolutions.postimees.utils;

import io.cify.framework.core.Device;

/**
 * Created by FOB Solutions
 */
public class VideoResult {

    public enum Result {
        SUCCESS,
        FAIL
    }

    private Result result;
    private String message;
    private String url;
    private String type;
    private Device device;
    private String sauceLabsUrl;

    public VideoResult(String url, boolean condition, String message, String type, Device device) {

        if (condition) {
            this.result = Result.SUCCESS;
        } else {
            this.result = Result.FAIL;
            this.message = message;
        }
        this.url = url;
        this.type = type;
        this.device = device;
        this.sauceLabsUrl = TestHelper.getPublicJobLink(device);
    }

    public Result getResult() {
        return result;
    }

    public Device getDevice() {
        return device;
    }

    public String getSauceLabsUrl() {
        return sauceLabsUrl;
    }

    @Override
    public String toString() {
        if (result == Result.FAIL) {
            return "result: " + result + ", message: " + message + ", url:" + url + ", videotype: " + type + System.lineSeparator() + deviceToString();
        } else {
            return "result: " + result + ", url:" + url + ", videotype: " + type + System.lineSeparator() + deviceToString();
        }
    }

    private String deviceToString() {
        return " device: [" + device.getCapabilities().toString() + "]";
    }
}
