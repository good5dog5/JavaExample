package com.Jordan.Example.csv;

import java.io.*;

public class CreateCsvExample {
    public static void main(String argv[]) throws IOException {
        FileWriter csvWriter = new FileWriter("file/big.csv");
        BufferedReader csvReader = new BufferedReader(new FileReader("file/supermarket_sales.csv"));
        String row = null;
        int i = 0;
        while ((row = csvReader.readLine()) != null) {
            System.out.println(i);
            if(i == 0) {
                csvWriter.append(row);
                csvWriter.append("\n");
            } else {
                for(int j = 0; j < 10000; j ++) {
                    csvWriter.append(row);
                    csvWriter.append("\n");
                }
            }
            i = i + 1;
        }
        csvWriter.flush();
        csvWriter.close();
        csvReader.close();
    }
}
