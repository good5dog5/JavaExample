package com.shark.example.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class ParserCsvOneLineExample {
    public static void main(String argv[]) throws IOException {
        String line = "SAN FANG (VIETNAM),MATERIALS,MATERIALS,NIKE,,SAN FANG,\"LO II-4, KCN MY XUAN A2\",TAN THANH,THANH PHO HO CHI MINH,10,VIETNAM,N/A,,,0.00%,0.00%,,,,,";
        CSVParser csvParser = new CSVParser();
        String[] words = csvParser.parseLine(line);
        for(String word: words) {
            System.out.print(word + "\\\t");
        }
    }
}
