package com.Jordan.Example.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DateFormatterExample {

    public static void main(String[] argv) {
        String time = "2019-11-04 11:33:45";
        List<String> datePatterns = Arrays.asList(
                "yyyy年MM月dd日HH時mm分ss秒", "yyyy年 MM月 dd日 HH時 mm分 ss秒", "yyyy 年 MM 月 dd 日 HH 時 mm 分 ss 秒",
                "yyyy年MM月dd日HH时mm分ss秒", "yyyy年 MM月 dd日 HH时 mm分 ss秒", "yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒",
                "dd-MM-yyyy-HH:mm:ss", "dd-MM-yyyy-HH:mm:ss-a", "dd/MM/yy HH:mm:ss",
                "dd-MM-yyyy-HH:mm:ss", "yyyy-MM-dd HH:mm:ss-SSS", "yyyy-MM-dd HH:mm:ss",
                "MMM dd,yyyy HH:mm:ss a", "MMM dd,yyyy hh:mm:ss a", "dd/MM/yyyy,HH:mm:ss a",
                "dd/MM/yyyy,hh:mm:ss a", "dd/MM/yyyy HH:mm:ss a", "dd/MM/yyyy hh:mm:ss a",
                "MM/dd/yyyy HH:mm", "yyyy/MM/dd HH:mm",
                "d/m-yyyy HH:mm:ss", "d.m.yyyy HH:mm:ss",
                "yyyy/MM/dd", "yyyy-MM-dd",
                "dd MM yyyy", "dd/MM/yy", "MM/dd/yyyy",
                "dd/MM/yyyy", "dd-MM-yyyy", "MM-dd-yyyy"
        );
//        List<DateTimeFormatter> formatters = datePatterns.stream().map(pattern -> DateTimeFormatter.ofPattern(pattern)).collect(Collectors.toList());

        for (String pattern : datePatterns) {
            DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern(pattern);
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(time, dateTimeFormatter);
                Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                System.out.println(pattern);
                localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                System.out.println(localDateTime.format(dateTimeFormatter));
            } catch (Exception ignored) {}
        }
    }
}
