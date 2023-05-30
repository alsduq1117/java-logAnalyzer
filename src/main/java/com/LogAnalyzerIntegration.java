package com;

import java.io.*;
import java.util.*;

public class LogAnalyzerIntegration {

    public static void main(String[] args) {
        String inputFile = "input.log";
        String outputFile = "output.log";

        Map<String, Integer> apiKeyCount = new HashMap<>();
        Map<Integer, Integer> statusCodeCount = new HashMap<>();
        Map<String, Integer> serviceIdCount = new HashMap<>();
        Map<String, Integer> browserCount = new HashMap<>();

        //로그 파일 읽어오기
        //[200][http://apis.cdg.com/search/knowledge?apikey=23jf&q=cdg][IE][2009-06-10 08:00:00]
        //[10][http://apis.cdg.com/search/news?q=cdg][IE][2009-06-10 08:01:37]
        //[404][http://apis.cdg.com/search/aaaaapikey=fqwk&q=cdg][IE][2009-06-10 08:04:53]
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));

            String log;
            while ((log = br.readLine()) != null) {
                    String[] seperate = log.split("[\\[\\]]"); //  [ 또는 ] 문자를 기준으로 문자열을 분할    ["", "상태코드", "", "URL", "" , "브라우저", "" , "시간대"]
                    int statusCode = Integer.parseInt(seperate[1]);
                    String url = seperate[3];
                    String time = seperate[7];
                    String apiKey;
                    if(url.indexOf("&") != -1) {
                        apiKey = url.substring(url.indexOf("apikey=") + 7, url.indexOf("&"));
                    } else {
                        apiKey = null;
                    }

                    String serviceId;
                    if(url.indexOf("?") != -1) {
                        serviceId = url.substring(url.indexOf("search/") + 7, url.indexOf("?"));
                    } else {
                        serviceId = null;
                    }
                    String browser = seperate[5];



                    apiKeyCount.put(apiKey, apiKeyCount.getOrDefault(apiKey, 0) + 1);

                    statusCodeCount.put(statusCode, statusCodeCount.getOrDefault(statusCode, 0) + 1);

                    serviceIdCount.put(serviceId, serviceIdCount.getOrDefault(serviceId, 0) + 1);

                    browserCount.put(browser, browserCount.getOrDefault(browser, 0) + 1);


            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //결과 출력
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            writer.write("최다호출 APIKEY\n\n");
            writer.write(getMaxApiKey(apiKeyCount) + "\n\n");
            writer.newLine();

            writer.write("상태코드 별 횟수\n\n");
            writer.write(getStatusCodeCount(statusCodeCount)+"\n\n");
            writer.newLine();

            writer.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n");
            writer.write(getTop3ServiceId(serviceIdCount)+"\n\n");
            writer.newLine();

//            writer.write("피크 시간대\n\n");
//            writer.write(peakTime);
//            writer.newLine();

            writer.write("웹 브라우저 별 사용 비율\n\n");
            writer.write(getBrowserUsage(browserCount));
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static String getBrowserUsage(Map<String, Integer> browserCount) {
        StringBuilder sb = new StringBuilder();
        int total = 0;

        for (int count : browserCount.values()) {
            total += count;
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(browserCount.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            String browser = entry.getKey();
            int count = entry.getValue();
            double ratio = (double) count / total * 100;
            sb.append(browser).append(" : ").append(String.format("%.1f%%", ratio)).append("\n");
        }

        return sb.toString();
    }

    private static String getTop3ServiceId(Map<String, Integer> serviceIdCount) {
        StringBuilder sb = new StringBuilder();

        serviceIdCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> sb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n"));


        return sb.toString();
    }

    private static String getStatusCodeCount(Map<Integer, Integer> statusCodeCount) {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Integer,Integer> entry : statusCodeCount.entrySet()){
            int statusCode = entry.getKey();
            int count = entry.getValue();
            sb.append(statusCode).append(" : ").append(count).append("\n");
        }

        return sb.toString();
    }

    private static String getMaxApiKey(Map<String, Integer> apiKeyCount) {
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : apiKeyCount.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        return maxEntry.getKey();
    }
}