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

        LogAnalyzerStarter logAnalyzerStarter = new LogAnalyzerStarter(inputFilePath,outputFilePath);
        logAnalyzerStarter.start();

    }
}
