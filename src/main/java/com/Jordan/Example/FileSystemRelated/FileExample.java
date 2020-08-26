package com.Jordan.Example.FileSystemRelated;

import java.io.File;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) throws IOException {
        String currentWorkingDirectory = new File(".").getAbsolutePath();

        File bands_txt = new File(currentWorkingDirectory + "/file/" + "bands.txt");
        printPaths(bands_txt);

    }

    private static void printPaths(File file) throws IOException {
        System.out.println("Abs       path : " + file.getAbsolutePath());
        System.out.println("Canonical path : " + file.getCanonicalPath());
        System.out.println("Path           : " + file.getPath());
    }
}
