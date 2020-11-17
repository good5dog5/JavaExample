package com.Jordan.Example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Testing {

    static Pattern algoIdPattern = Pattern.compile("\\d{17}_CM@(SGP|BG|SG)");
    private static String extractAlgoId(String fileName) {
        Matcher matcher = algoIdPattern.matcher(fileName);

        if(matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    private static void testCharArray() {
        char[] charArray = ")]}'\n".toCharArray();
        System.out.println(charArray);

    }
    public static void main(String[] args) throws ParseException, IOException {
//        List<String> fileNames = [
        String [] fileNames = {
                "3_ABB247035200485G_20200701114134247_CM@SGP_254_thumbnail.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_258.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_265.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_316_thumbnail.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_376.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_387_thumbnail.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_399_thumbnail.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_404.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_414.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_417.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_424.png",
                "3_ABB247035200485G_20200701114134247_CM@SGP_431.png",
                "3_ABB247035200485G_20200701114134247_CM@SG_431.png",
                "3_ABB247035200485G_20200701114134247_CM@BM.png",
                "3_ABB247035200485G_20200701114134247_CM@BG.png",
                };

        List<Path> cmFilePaths = Files.walk(Paths.get("/opt/fuGlass/commonMaterial"))
                .filter(p -> !Files.isDirectory(p))
                .collect(Collectors.toList());
//        for(Path filePath : cmFilePaths) {
////            System.out.println(filePath.getFileName().toString() + " | " + extractAlgoId(filePath.getFileName().toString()));
//            if(extractAlgoId(filePath.getFileName().toString()) == null) {
//                System.out.println(filePath.getFileName().toString());
////                FileUtils.deleteQuietly(filePath.toFile());
//                System.out.println(filePath.toString());
//            }
//        }

        for (String filenmae : fileNames) {
            if(extractAlgoId(filenmae) != null) {
                System.out.println(extractAlgoId(filenmae));
            } else {
                System.out.println("Null");
            }
        }
        testCharArray();

        for(int i=0; i<=0x1f; i++) {
            System.out.println(i);
            System.out.println(String.format("\\u%04x", (int) i));
        }
    }
}
