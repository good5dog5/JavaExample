package com.Jordan.Example.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntStreamExample {
    public static void main(String[] args) {

        // use IntStream
        int [] arr = IntStream
                .rangeClosed(0, 10)
                .map(s-> s*2).toArray();


        System.out.println(Arrays.toString(arr));
    }
}
