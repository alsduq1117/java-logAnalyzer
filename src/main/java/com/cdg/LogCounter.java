package com.cdg;

import java.util.List;
import java.util.Map;

public class LogCounter {

    public LogData countLogs(List<Log> logs) {
        LogData logData = new LogData();


        for (Log log : logs) {
            logData.getStatusCodeCount().put(log.getStatuscode(),  logData.getStatusCodeCount().getOrDefault(log.getStatuscode(), 0) + 1);
            logData.getApiKeyCount().put(log.getApiKey(), logData.getApiKeyCount().getOrDefault(log.getApiKey(), 0) + 1);
            logData.getServiceIdCount().put(log.getServiceId(), logData.getServiceIdCount().getOrDefault(log.getServiceId(), 0) + 1);
            logData.getBrowserCount().put(log.getBrowser(), logData.getBrowserCount().getOrDefault(log.getBrowser(), 0) + 1);
        }

        return logData;
    }

}
