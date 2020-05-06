package com.Jordan.Example.csv;

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
        String fileName = "blank_error.csv";
        long startTime = System.currentTimeMillis();
//        FileReader fileReader = new FileReader("file/big.csv");
        File file = new File("file/" + fileName);
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
//        long endTime = System.currentTimeMillis();
//        System.out.println("total time: " + (endTime - startTime));
//        FileReader fileReader = new FileReader("file/" + fileName, Charset.forName(encoding));
//        CSVReader csvReader = new CSVReader(fileReader);
//        String[] nextRecord;
//        while ((nextRecord = csvReader.readNext()) != null) {
//            for (String cell : nextRecord) {
//                System.out.println("cell: " + cell);
//            }
//        }
//        csvReader.close();
//        long endTime = System.currentTimeMillis();
        System.out.println("total time: ");
    }
}
