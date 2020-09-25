package com.Jordan.Example.FileSystemRelated;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PathExample {
    public static void main(String[] args) throws IOException {
        System.out.println(pathJoinExample());

//        Path currentWorkingPath = Paths.get("").toAbsolutePath();
//        Path path = Paths.get(currentWorkingPath.toString() + "/file/"  + "band.txt");
//
//        String currentWorkingDirectory = new File(".").getAbsolutePath();
//        File bands_txt = new File(currentWorkingDirectory + "/file/" + "bands.txt");
//
//
//        // file to path
//        Path convertedPath =  bands_txt.toPath();
//        File converedFile = path.toFile();
//
//        System.out.println("abs path: " + path.toString());
//        System.out.println("getFileName(): " + path.getFileName());
//        System.out.println("getName(0): " + path.getName(0));
//        System.out.println("getNameCount(): " + path.getNameCount());
//        System.out.println("subPath(0, 2): " + path.subpath(0, 2));
//        System.out.println("getParent(): " + path.getParent());
//        System.out.println("getRoot(): " + path.getRoot());
//
//        Stream<Path> javaPaths = generatePaths();
//        javaPaths.forEach(System.out::println);
    }

    private static Stream<Path> generatePaths() throws IOException {
        return Files.walk(Paths.get(""))
                .filter(p -> p.getFileName().endsWith("java"));

    }

    private static Path pathJoinExample() {
        Path path = Paths.get("").toAbsolutePath();
        return Paths.get(path.toString(), "foo", "bar", "baz.txt");
    }
}
