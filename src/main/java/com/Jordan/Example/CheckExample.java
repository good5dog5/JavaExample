package com.shark.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckExample {
    public static void main(String argv[]) {
        long startTime = System.currentTimeMillis();
        List<CheckEntity> checkEntityList = new ArrayList<>();
        for(int i = 0; i < 60000; i++) {
            CheckEntity checkEntity = new CheckEntity(Arrays.asList("2018-11-20", "123", "abc"));
            checkEntityList.add(checkEntity);
        }
        for(CheckEntity checkEntity: checkEntityList) {
            for(String value: checkEntity.valueList) {
                try {
                    DateTimeFormatter.BASIC_ISO_DATE.parse(value);
                    DateTimeFormatter.ISO_DATE.parse(value);
                    DateTimeFormatter.ISO_DATE_TIME.parse(value);
                    DateTimeFormatter.ISO_INSTANT.parse(value);
                    DateTimeFormatter.ISO_LOCAL_DATE.parse(value);
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(value);
                    DateTimeFormatter.ISO_LOCAL_TIME.parse(value);
                    DateTimeFormatter.ISO_OFFSET_DATE.parse(value);
                    DateTimeFormatter.ISO_OFFSET_DATE.parse(value);
                    Long.valueOf(value);
                    Double.valueOf(value);
                } catch (Exception e) {
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }



    private static class CheckEntity {
        List<String> valueList;

        public CheckEntity(List<String> valueList) {
            this.valueList = valueList;
        }
    }
}
