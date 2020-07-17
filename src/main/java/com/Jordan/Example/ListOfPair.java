package com.Jordan.Example;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListOfPair {
    public static void main(String[] args) {
        SimpleDateFormat s = new SimpleDateFormat("dd");
        int d = Integer.parseInt(s.format(new Date()));
        System.out.println(d);
//        List<String> productIds= Arrays.asList("ABC#1", "ABC#2", "CDE#3", "GEF#9", "GEF#9");
//        List<Pair<String, String>> prdPair = new ArrayList<>();
//
//        for(String s : productIds) {
//            String[] res = s.split("#");
//            prdPair.add(ImmutablePair.of(res[0], s));
//        }
//
//        prdPair.forEach(System.out::println);
//
//        List<String> temp = prdPair.stream().map(Pair::getLeft).collect(Collectors.toList());
//        System.out.println(temp);
//
//        List<String> orig = prdPair.stream().map(Pair::getRight).collect(Collectors.toList());
//        System.out.println(orig);


    }
}
