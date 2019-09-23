package com.shark.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalenderExample {
    public static void main(String[] argv) {
        String dateString = "2019-08-05";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startCalendar = new GregorianCalendar();
        try {
            startCalendar.setTime(simpleDateFormat.parse(dateString));
            startCalendar.set(Calendar.DAY_OF_WEEK, 1);
            System.out.println(simpleDateFormat.format(startCalendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
