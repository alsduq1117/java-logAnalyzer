package com.cdg;

public class LogResultData {

    private String maxApiKey;
    private String countByStatusCode;
    private String top3ServiceId;
    private String browserUsage;

    public String getMaxApiKey() {
        return maxApiKey;
    }

    public String getCountByStatusCode() {
        return countByStatusCode;
    }

    public String getTop3ServiceId() {
        return top3ServiceId;
    }

    public String getBrowserUsage() {
        return browserUsage;
    }

    public LogResultData(String maxApiKey, String countByStatusCode, String top3ServiceId, String browserUsage) {
        this.maxApiKey = maxApiKey;
        this.countByStatusCode = countByStatusCode;
        this.top3ServiceId = top3ServiceId;
        this.browserUsage = browserUsage;
    }
}
