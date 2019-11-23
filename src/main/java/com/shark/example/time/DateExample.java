package com.shark.example.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExample {

    public static void main(String argv[]) {
        String testData1 = "2019/10/10 8:50";
        try {
            new SimpleDateFormat("yyyy/MM/dd").parse(testData1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String testData2 = "2019/10/10";
        try {
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").parse(testData2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String testData3 = "2019/10/10 123456789";
        try {
            new SimpleDateFormat("yyyy/MM/dd").parse(testData3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
