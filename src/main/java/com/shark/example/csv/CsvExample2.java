package com.shark.example.csv;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvExample2 {
    public static void main(String argv[]) throws IOException {

        FileReader fileReader = new FileReader("file/Nike Factory Locations.csv");
        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;

        while ((nextRecord = csvReader.readNext()) != null) {
            for (String cell : nextRecord) {
                System.out.print(cell + "\\\t");
            }
            System.out.println();
        }
        csvReader.close();
    }
}
