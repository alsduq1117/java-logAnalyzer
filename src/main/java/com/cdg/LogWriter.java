package com.cdg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    private String outputfile;

    public LogWriter(String outputfile) {
        this.outputfile = outputfile;
    }

    public void writeLogs(LogResultData logResultData) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile));

            bw.write("최다호출 APIKEY\n\n");
            bw.write(logResultData.getMaxApiKey() + "\n\n");
            bw.newLine();

            bw.write("상태코드 별 횟수\n\n");
            bw.write(logResultData.getCountByStatusCode() + "\n");
            bw.newLine();

            bw.write("상위 3개의 API ServiceID와 각각의 요청 수\n\n");
            bw.write(logResultData.getTop3ServiceId() + "\n");
            bw.newLine();

            bw.write("피크 시간대\n\n");
            bw.write(logResultData.getPeakTime() + "\n\n");
            bw.newLine();

            bw.write("웹 브라우저 별 사용 비율\n\n");
            bw.write(logResultData.getBrowserUsage());
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
