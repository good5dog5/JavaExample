package com.shark.example;

import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String argv[]) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        double size = 10;
        double count = Math.ceil(list.size() / size);
        System.out.println("count: " + count);
        for(int i = 0; i < count; i ++) {
            int start = (int)(i * size);
            int end = (int)(start + size);
            if(end >= list.size()) {
                end = list.size();
            }
            List subList = list.subList(start, end);
            System.out.println("subList: " + subList.toString());
        }
    }
}
