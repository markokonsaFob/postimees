package com.fobsolutions.postimees.utils;

import io.cify.framework.core.Device;

/**
 * Created by FOB Solutions
 */
public class VideoResult {

    private Result result;
    private String message;
    private String url;
    private String type;
    private Device device;

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
    }

    public Result getResult() {
        return result;
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

    public enum Result {
        SUCCESS,
        FAIL
    }
}
