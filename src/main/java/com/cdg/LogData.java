package com.cdg;

import java.util.HashMap;
import java.util.Map;

public class LogData {

    private Map<Integer, Integer> statusCodeCount;
    private Map<String, Integer> apiKeyCount;
    private Map<String, Integer> serviceIdCount;
    private Map<String, Integer> browserCount;
    private Map<String, Integer> timeCount;


    public LogData() {
        statusCodeCount = new HashMap<>();
        apiKeyCount = new HashMap<>();
        serviceIdCount = new HashMap<>();
        browserCount = new HashMap<>();
        timeCount = new HashMap<>();
    }

    public Map<Integer, Integer> getStatusCodeCount() {
        return statusCodeCount;
    }

    public Map<String, Integer> getApiKeyCount() {
        return apiKeyCount;
    }

    public Map<String, Integer> getServiceIdCount() {
        return serviceIdCount;
    }

    public Map<String, Integer> getBrowserCount() {
        return browserCount;
    }

    public Map<String, Integer> getTimeCount() {
        return timeCount;
    }
}
