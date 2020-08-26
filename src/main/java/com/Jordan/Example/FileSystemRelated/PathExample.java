package com.Jordan.Example.FileSystemRelated;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) {

        Path currentWorkingPath = Paths.get("").toAbsolutePath();
        Path path = Paths.get(currentWorkingPath.toString() + "/file/"  + "band.txt");

        String currentWorkingDirectory = new File(".").getAbsolutePath();
        File bands_txt = new File(currentWorkingDirectory + "/file/" + "bands.txt");


        // file to path
        Path convertedPath =  bands_txt.toPath();
        File converedFile = path.toFile();

        System.out.println("abs path: " + path.toString());
        System.out.println("getFileName(): " + path.getFileName());
        System.out.println("getName(0): " + path.getName(0));
        System.out.println("getNameCount(): " + path.getNameCount());
        System.out.println("subPath(0, 2): " + path.subpath(0, 2));
        System.out.println("getParent(): " + path.getParent());
        System.out.println("getRoot(): " + path.getRoot());
    }
}
