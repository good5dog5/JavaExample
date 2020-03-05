package com.shark.example.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileExample {
    public static void main(String[] argv) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "file/data.txt"));
            String line = reader.readLine();
            while (line != null) {

                String[] array = line.split(",");
                String alias = array[1];
                String name = array[0];
                String sql = "UPDATE datacolumn SET primary_alias = '" + alias + "' WHERE datacolumn.name = '" + name + "' and dataframe_id = 8;";
                System.out.println(sql);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
