package com.Jordan.Example.list;

import java.util.ArrayList;
import java.util.List;

public class ContainExample {
    public static void main(String[] argv) {
        List<Long> list = new ArrayList<>();
        long value1 = 1L;
        list.add(value1);
        value1 = 1L;
        boolean contain = list.contains(value1);
        System.out.println("contain: " + contain);
    }
}
