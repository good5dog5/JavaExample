package com.shark.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParserExample {

    public static void main(String argv[]) {
        String command = "${income} - ${cost}";
        List<String> valueList = Arrays.stream(command.split("\\$")).filter(value -> value.contains("{") && value.contains("}"))
                .map(value -> value.split("}")[0].split("\\{")[1]).collect(Collectors.toList());

        for(String value: valueList) {
            System.out.println("value: " + value);
        }
    }

    public static String map(String value) {
        return value.split("}")[0].split("\\{")[1];
    }
}
