package com.Jordan.Example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class BashCommand {
    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("which python");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            File pyPath = new File(in.readLine());
            System.out.println(pyPath.getCanonicalPath());
        }
    }
}
