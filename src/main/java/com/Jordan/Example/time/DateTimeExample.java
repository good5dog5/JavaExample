package com.Jordan.Example.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeExample {

    public static void main(String argv[]) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -5);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }
}

