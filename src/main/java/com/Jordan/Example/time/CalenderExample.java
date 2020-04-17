package com.shark.example.time;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String today = simpleDateFormat.format(calendar.getTime());
        System.out.println("today: " + today);
        long currentTime = calendar.getTimeInMillis();
        System.out.println("currentTime: " + currentTime);
        //two week ago date
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, -2);
        List<String> timeList = new ArrayList<>();
        while (calendar.getTimeInMillis() <= currentTime) {
            timeList.add(simpleDateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        System.out.println(new Gson().toJson(timeList));
    }
}
