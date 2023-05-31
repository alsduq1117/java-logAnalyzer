package com.cdg;

import java.util.List;

public class LogAnalyzerStarter {
    private String inputFilePath;
    private String outputFilePath;

    public LogAnalyzerStarter(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public void start(){
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
