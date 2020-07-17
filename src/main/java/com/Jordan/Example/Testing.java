package com.Jordan.Example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class Testing {
    public static void main(String[] args) throws ParseException {
//        String aString = "G|G";
//        String[] thickColorPair = aString.split("\\|");
//        String[] pair = thickColorPair[0].split("((?<=[A-Za-z])|(?=[A-Za-z]))");
//        System.out.println(pair.length == 2);
//        System.out.println(Arrays.toString(thickColorPair));
//        System.out.println(Arrays.toString(pair));
//        System.out.println("done");
//        System.out.println("done");
//        Boolean testBoolean =  null;
//        System.out.println(testBoolean);

//        boolean aBoolean = Boolean.parseBoolean("T");
//        System.out.println(aBoolean);
//        String aString = "2.1G|1.6G446B_machine";
//        System.out.println(aString.split("_")[1]);

//        String test = String.format("%.0f", Double.parseDouble("2.102") * 100)  + "aaa";
//        System.out.println(test);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date aDate = formatter.parse("2015-12-28 00:00:00");

        LocalDateTime localDateTime = LocalDateTime.ofInstant(aDate.toInstant(), ZoneId.of("UTC"));
        String value1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-'W'ww"));
        String value2 = localDateTime.format(DateTimeFormatter.ofPattern("YYYY-'W'ww"));
        System.out.println(value1);
        System.out.println(value2);



    }
}
