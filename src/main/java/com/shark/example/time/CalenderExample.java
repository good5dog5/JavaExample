package com.shark.example.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalenderExample {
    public static void main(String[] argv) {
//        String dateString = "2019-08-05";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd SS.sss");
//        Calendar startCalendar = new GregorianCalendar();
//        try {
//            startCalendar.setTime(simpleDateFormat.parse(dateString));
//            startCalendar.set(Calendar.DAY_OF_WEEK, 1);
//            System.out.println(simpleDateFormat.format(startCalendar.getTime()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ss.SSS");
        Calendar week1Calendar = new GregorianCalendar();
        System.out.println("week1Calendar: " + simpleDateFormat.format(week1Calendar.getTime()));
        week1Calendar.add(Calendar.WEEK_OF_MONTH, -1);
        week1Calendar.set(Calendar.HOUR, 0);
        week1Calendar.set(Calendar.SECOND, 0);
        week1Calendar.set(Calendar.MILLISECOND, 0);
        System.out.println("week1Calendar: " + simpleDateFormat.format(week1Calendar.getTime()));
    }
}
