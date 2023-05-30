package com.cdg;

import java.util.*;

public class LogAnalyzer {


    public LogResultData analyzeLogs(LogData logData) {

        String maxApiKey = getMaxApiKey(logData);
        String countByStatusCode = getCountByStatusCode(logData);
        String top3ServiceId = getTop3ServiceId(logData);
        String browserUsage = getBrowserUsage(logData);
        String peakTime = getPeakTime(logData);


        return new LogResultData(maxApiKey, countByStatusCode, top3ServiceId, browserUsage, peakTime);
    }

    private String getPeakTime(LogData logData) {
        Map.Entry<String, Integer> peakTimeEntry = null;
        for (Map.Entry<String, Integer> entry : logData.getTimeCount().entrySet()) {

            if (peakTimeEntry == null || entry.getValue() > peakTimeEntry.getValue()) {
                peakTimeEntry = entry;
            }
        }
        return peakTimeEntry.getKey();
    }

    private String getBrowserUsage(LogData logData) {
        int total = 0;
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(logData.getBrowserCount().entrySet());
        // 엔트리 값을 기준으로 내림차순 정렬
        entryList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        for (Map.Entry<String, Integer> entry : entryList) {
            total += entry.getValue();
        }
        for (Map.Entry<String, Integer> entry : entryList) {
            String browserName = entry.getKey();
            Integer count = entry.getValue();
            sb.append(String.format("%s : %.1f%%", browserName, ((float) count / total * 100)) + "\n");
        }

        return sb.toString();
    }

    private String getTop3ServiceId(LogData logData) {
        StringBuilder sb = new StringBuilder();

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(logData.getServiceIdCount().entrySet());

        // 엔트리 값을 기준으로 내림차순 정렬
        entryList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        // 상위 3개의 엔트리를 추출
        List<Map.Entry<String, Integer>> topEntries = entryList.subList(0, 3);

        for (Map.Entry<String, Integer> entry : topEntries) {
            sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
        }

        return sb.toString();
    }

    private String getCountByStatusCode(LogData logData) {
        StringBuilder sb = new StringBuilder();


        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(logData.getStatusCodeCount().entrySet());

        // 엔트리 키를 기준으로 오름차순 정렬
        entryList.sort(Map.Entry.comparingByKey());

        for (Map.Entry<Integer, Integer> entry : entryList) {
            Integer statusCode = entry.getKey();
            Integer count = entry.getValue();
            sb.append(statusCode + " : " + count + "\n");
        }
        return sb.toString();
    }

    private String getMaxApiKey(LogData logData) {
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : logData.getApiKeyCount().entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }


}
