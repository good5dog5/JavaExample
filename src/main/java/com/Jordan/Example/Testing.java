package com.Jordan.Example;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class Testing {
    private static final Map<String, String> digitMap = new HashMap<String, String>() {{
        put("１", "1");
        put("２", "2");
        put("３", "3");
        put("４", "4");
        put("５", "5");
        put("６", "6");
        put("７", "7");
        put("８", "8");
        put("９", "9");
        put("一", "1");
        put("二", "2");
        put("三", "3");
        put("四", "4");
        put("五", "5");
        put("六", "6");
        put("七", "7");
        put("八", "8");
        put("九", "9");
        put("壹", "1");
        put("貳", "2");
        put("叁", "3");
        put("肆", "4");
        put("伍", "5");
        put("陸", "6");
        put("柒", "7");
        put("捌", "8");
        put("玖", "9");
    }};


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

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date aDate = formatter.parse("2015-12-28 00:00:00");
//
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(aDate.toInstant(), ZoneId.of("UTC"));
//        String value1 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-'W'ww"));
//        String value2 = localDateTime.format(DateTimeFormatter.ofPattern("YYYY-'W'ww"));
//        System.out.println(value1);
//        System.out.println(value2);
//
//        String orig = "一二五捌311八九";
//        Set<Character> aSet = new HashSet<>();
//
//        for(String key : digitMap.keySet()) {
//            orig = orig.replaceAll(key, digitMap.get(key));
//        }
//
//        for(char c : orig.toCharArray()) {
//            aSet.add(c);
//        }
//        System.out.println(orig);
//        System.out.println(aSet);

        String aString = "2.1G|1.6G446B";
        String[] colors = aString.split("\\|");
        System.out.println(colors);

        for (String color : colors) {
            System.out.println(color.replaceAll("\\d|\\.", ""));

        }

    }
}
