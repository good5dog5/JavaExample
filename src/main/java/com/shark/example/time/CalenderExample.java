package com.shark.example.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();
        System.out.println("CURRENT: " + simpleDateFormat.format(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        System.out.println("DAY_OF_WEEK: " + simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.WEEK_OF_MONTH, -2);
        System.out.println("TWO WEEK AGE: " + simpleDateFormat.format(calendar.getTime()));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println("week1Calendar: " + simpleDateFormat.format(calendar.getTime()));
    }
}
