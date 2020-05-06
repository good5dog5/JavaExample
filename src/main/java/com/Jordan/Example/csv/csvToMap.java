package com.Jordan.Example.csv;

import com.opencsv.CSVParser;
import org.apache.kafka.common.protocol.types.Field;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class csvToMap {
    public static void main(String argv[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file/product_direction.csv"));
        Map<String, String> map = br.lines()
                .skip(1)
                .map(s -> s.split(","))
                .collect(Collectors.toMap(str->str[0], str->str[1]));

        System.out.println(map);
    }
}
