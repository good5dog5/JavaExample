package com.Jordan.Example.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntStreamExample {
    public static void main(String[] args) {
        int [] arr = new int[5];
        Arrays.fill(arr, 10);

        // use IntStream
        int [] arr1 = IntStream.rangeClosed(1, 5).map(s->10).toArray();
        System.out.println(Arrays.toString(arr1));
    }
}
