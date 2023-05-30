package com.cdg;

public class Log {
    private int statuscode;
    private String apiKey;
    private String serviceId;
    private String browser;

    private String time;


    public Log(int statuscode, String apiKey, String serviceId, String browser, String time) {
        this.statuscode = statuscode;
        this.apiKey = apiKey;
        this.serviceId = serviceId;
        this.browser = browser;
        this.time = time;
    }


    public int getStatuscode() {
        return statuscode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getBrowser() {
        return browser;
    }

    public String getTime() {
        return time;
    }
}
