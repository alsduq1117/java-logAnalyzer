package com.cdg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        String inputFilePath = "input.log";
        String outputFilePath = "output.log";

        //읽어 오기
        LogReader logReader = new LogReader(inputFilePath);
        List<Log> logs = logReader.readLogs();

        //count 하기
        LogCounter logCounter = new LogCounter();
        LogData logData = logCounter.countLogs(logs);

        //분석 하기
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        LogResultData logResultData = logAnalyzer.analyzeLogs(logData);

        //쓰기
        LogWriter logWriter = new LogWriter(outputFilePath);
        logWriter.writeLogs(logResultData);


    }
}
