package com.shark.example.list;

import java.util.ArrayList;
import java.util.List;

public class ContainExample {
    public static void main(String[] argv) {
        List<Long> list = new ArrayList<>();
        Long value1 = Long.valueOf(1);
        list.add(value1);
        value1 = Long.valueOf(1);
        boolean contain = list.contains(value1);
        System.out.println("contain: " + contain);
    }
}
