package com.cdg;

public class Log {
    private int statuscode;
    private String apiKey;
    private String serviceId;
    private String browser;


    public Log(int statuscode, String apiKey, String serviceId, String browser) {
        this.statuscode = statuscode;
        this.apiKey = apiKey;
        this.serviceId = serviceId;
        this.browser = browser;
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
}
