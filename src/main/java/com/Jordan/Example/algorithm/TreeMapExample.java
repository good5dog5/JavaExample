package com.Jordan.Example.algorithm;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<Integer, String>();
        map.put(1, "one");
        map.put(3, "three");
        map.put(2, "two");
        // prints one two three
        for(Integer key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
