package com.Jordan.Example.time;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

// ref https://robertsong.pixnet.net/blog/post/347397794-%5B%E7%A8%8B%E5%BC%8F%5D%5Bjava%5D%5B%E8%BD%89%E8%B2%BC%5D-java8-%E6%97%A5%E6%9C%9F-%E6%99%82%E9%96%93%EF%BC%88date-time%EF%BC%89api
public class LocalDateExample {

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        System.out.println("current date: " + today);

        LocalDate firstDayOf2014 = LocalDate.of(2014, Month.JANUARY, 1);
        System.out.println("Specific Date : " + firstDayOf2014);

        LocalDate todayKolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current date in IST : " + todayKolkata);

        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("365 day from base Date : " + dateFromBase);

    }

}
