package com.shark.example.csv;

import com.opencsv.CSVReader;
import org.mozilla.universalchardet.UniversalDetector;
import util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ParserCsvExample {
    public static void main(String argv[]) throws IOException {
        long startTime = System.currentTimeMillis();
//        FileReader fileReader = new FileReader("file/big.csv");
        File file = new File("file/SalesForCourse_CN_BIG5_test.csv");
        byte[] buff = new byte[4096];
        FileInputStream fileInputStream = new FileInputStream(file);
        UniversalDetector detector = new UniversalDetector(null);
        int read;
        while ((read = fileInputStream.read(buff)) > 0 && !detector.isDone()) {
            detector.handleData(buff, 0, read);
        }
        detector.dataEnd();

        String encoding = detector.getDetectedCharset();
        if (StringUtil.isEmpty(encoding)) {
            encoding = "UTF-8";
        }
        detector.reset();
        System.out.println("encoding: " + encoding);
        FileReader fileReader = new FileReader("file/SalesForCourse_CN_BIG5_test.csv", Charset.forName(encoding));
        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            for (String cell : nextRecord) {
                System.out.println("cell: " + cell);
            }
        }
        csvReader.close();
        long endTime = System.currentTimeMillis();
        System.out.println("total time: " + (endTime - startTime));
    }
}
