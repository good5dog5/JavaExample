package com.Jordan.Example;

import java.io.*;

public class ReadTxtExample {
    public static void main(String[] argv) {
        File file = new File("file/sample.txt");
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
