package com.cdg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class LogReader {

    private String inputfile;

    public LogReader(String inputfile) {
        this.inputfile = inputfile;
    }


    public List<Log> readLogs() {
        List<Log> logArrayList = new ArrayList<Log>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputfile));

            String logString;
            while ((logString = br.readLine()) != null) {
                Log log = separateLog(logString);
                logArrayList.add(log);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return logArrayList;

    }


    public Log separateLog(String logString){
        String[] seperate = logString.split("[\\[\\]]");

        int statsusCode = Integer.parseInt(seperate[1]);
        String url = seperate[3];
        String apiKey = extractApiKey(url);
        String serviceId = extractServiceId(url);
        String browser = seperate[5];
        
        return new Log(statsusCode,apiKey,serviceId,browser);

    }

    private String extractServiceId(String url) {
        String serviceId;
        if (url.indexOf("?") != -1) {
            serviceId = url.substring(url.indexOf("search/") + 7, url.indexOf("?"));
        } else {
            serviceId = null;
        }
        return serviceId;
    }

    private String extractApiKey(String url) {
        String apiKey;
        if(url.indexOf("&") != -1) {
            apiKey = url.substring(url.indexOf("apikey=") + 7, url.indexOf("&"));
        } else {
            apiKey = null;
        }
        return apiKey;
    }


}



