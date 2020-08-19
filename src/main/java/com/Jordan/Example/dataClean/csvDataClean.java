package com.Jordan.Example;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class csvDataClean {

    private static final String inputFile = "/Users/jordan/Downloads/20200717_process_info_test.xlsx";
    private static final String outputFile = "/Users/jordan/Downloads/20200717_process_info.csv";
    private static final Pattern thicknessDeviationPattern = Pattern.compile( "([-]|[+]|[±])?[0-9]+[.]?[0-9]+");
    private static Map<String, Integer> column2IndexMap = null;
    private static final Map<String, String> colorMap = new HashMap<String, String>() {{
        put("绿", "G");
        put("白", "C");
        put("深", "YP");
        put("灰", "YT");
        put("YJ", "YJ");
        put("YT", "YT");
        put("兰", "B");
        put("蓝", "B");
        put("茶", "Z");
        put("LOW-E", "C");
    }};
    private static final Map<Character, Character> digitMap = new HashMap<Character, Character>() {{
        put('１', '1');
        put('２', '2');
        put('３', '3');
        put('４', '4');
        put('５', '5');
        put('６', '6');
        put('７', '7');
        put('８', '8');
        put('９', '9');
        put('一', '1');
        put('二', '2');
        put('三', '3');
        put('四', '4');
        put('五', '5');
        put('六', '6');
        put('七', '7');
        put('八', '8');
        put('九', '9');
        put('壹', '1');
        put('貳', '2');
        put('叁', '3');
        put('肆', '4');
        put('伍', '5');
        put('陸', '6');
        put('柒', '7');
        put('捌', '8');
        put('玖', '9');
    }};

    private static final NavigableMap<Double, Double> thicknessRangeMap = new TreeMap<Double, Double>() {{
        put(0d, 1.4d);
        put(1.4d, 1.6d);
        put(1.6d, 1.8d);
        put(1.8d, 2d);
        put(2d, 2.1d);
        put(2.1d, 2.2d);
        put(2.2d, 2.3d);
        put(2.3d, 2.5d);
        put(2.6d, 3.0d);
        put(3.1d, 3.2d);
        put(3.2d, 3.5d);
        put(3.75d, 4.0d);
        put(4.7d, 5d);
        put(5d, 6d);
        put(7d, 8d);
        put(10d, 12d);
        put(13d, 14d);
        put(14d, 15d);
        put(15d, 16d);
        put(16d, 18d);
        put(18d, 20d);

    }};

    public static void main(String[] args) throws IOException, InvalidFormatException {
        readExcel(inputFile, outputFile);
    }

    public static void printSummary(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        System.out.println("工作表名称：" + sheetName);

        // 获取当前Sheet的总行数
        int rowsOfSheet = sheet.getLastRowNum();
        System.out.println("当前表格的总行数:" + rowsOfSheet);
    }

    public static Map<String, Integer> buildColumnNameToIdxMap(Sheet sheet) {
        Map<String, Integer> map = new HashMap<>();
        Row row = sheet.getRow(0);

        short minColIx = row.getFirstCellNum();
        short maxColIx = row.getLastCellNum();
        for (short colIx = minColIx; colIx < maxColIx; colIx++) {
            Cell cell = row.getCell(colIx);
            map.put(cell.getStringCellValue(), cell.getColumnIndex());
        }

        return map;
    }

    private static void setColumn2IndexMap(Sheet sheet) {
        column2IndexMap = buildColumnNameToIdxMap(sheet);

    }

    private static int indexOfCol(String columnName) {
        return column2IndexMap.get(columnName);
    }

    private static boolean fileExist(String filePath) {
        return new File(filePath).exists();
    }

    private static Sheet  getFirstSheet(String inputFile) throws IOException, InvalidFormatException {

        if (!fileExist(inputFile)) {
            System.out.println("文件不存在!");
        }

        Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(inputFile)));
        return workbook.getSheetAt(0);

    }

    private static String generateProperty (Cell cell) {

        if (cell == null) {
            return "side_window";
        }

        String origProperty = cell.getStringCellValue();
        switch (origProperty) {
            case "钢化挡风" :
                return "back_wind_shield";
            case "挡风":
            case "镀膜":
                return "front_wind_shield";
            case "夹丝":
            case "天窗":
                return "sun_roof";
            default:
                return "side_window";
        }
    }

    private static String generateMarketProperty (Cell cell) {
        if (cell == null) {
            return "accessory";
        }
        String origProperty = cell.getStringCellValue();
        return "配套".equals(origProperty) ? "kit" : "accessory";
    }

    private static Pair<Double, Double> generateThicknessDeviation (Cell cell, int rowNum) {
        if (cell == null) {
            return new ImmutablePair<>(-0.2, 0.2);
        }

        double upperBound = 0;
        double lowerBound = 0;
        String target = cell.getStringCellValue();
        Matcher matcher = thicknessDeviationPattern.matcher(target);
        List<String> result = new ArrayList<>();

        while (matcher.find()) {
            result.add(matcher.group());
        }

        int lenth = result.size();

        if (lenth == 2) {
            try {
                double a = Double.parseDouble(result.get(0));
                double b = Double.parseDouble(result.get(1));
                if (a > b) {
                    upperBound = a;
                    lowerBound = b;
                } else {
                    upperBound = b;
                    lowerBound = a;
                }
            } catch (Exception e) {
                System.out.println(rowNum + "转换行数字" + result.get(0) + " ; " + result.get(1));
            }
        } else if (lenth == 1) {
            String frist = result.get(0).substring(0, 1);
            if (frist.equals("±")) {
                try {
                    upperBound = Double.parseDouble(result.get(0).substring(1));
                    lowerBound = Double.parseDouble("-" + result.get(0).substring(1));
                } catch (Exception e) {
                    System.out.println(rowNum + "转换行数字" + result.get(0));
                }
            } else {
                try {
                    upperBound = Double.parseDouble(result.get(0));
                    lowerBound = upperBound;
                } catch (Exception e) {
                    System.out.println(rowNum + "转换行数字" + result.get(0));
                }
            }
        } else if (lenth == 3) {
            try {
                double a = Double.parseDouble(result.get(0));
                double b = Double.parseDouble(result.get(1));
                double c = Double.parseDouble(result.get(2));
                upperBound = lowerBound = a;
                if (b > upperBound) {
                    upperBound = b;
                } else {
                    lowerBound = b;
                }
                if (c > upperBound) {
                    lowerBound = upperBound;
                    upperBound = c;
                } else {
                    if (c < lowerBound) {
                        lowerBound = c;
                    }
                }
            } catch (Exception e) {
                System.out.println(rowNum + "转换行数字出错" + result.get(0));
            }
        } else {
            upperBound = 0.1;
            lowerBound = -0.1;
        }
        return new ImmutablePair<>(lowerBound, upperBound);
    }

    private static String generateDxfId(Cell cell) {
        return isStandardImage(cell) ? "standard_image" : cell.getStringCellValue();
    }

    private static boolean isStandardImage(Cell cell) {
        if (cell == null) {
            return true;
        }

        String currDxfId = cell.getStringCellValue();

        return currDxfId.contains("其")
                || currDxfId.contains("图")
                || currDxfId.contains("方形")
                || currDxfId.contains("标")
                || currDxfId.contains("大片")
                || currDxfId.contains("靠模")
                || currDxfId.equals("0");
    }

    private static String generateCutType(Cell cell) {
       if (cell == null) {
           return "machine";
       }
       return cell.getStringCellValue().contains("手") ? "hand" : "machine";
    }

    private static String generateCompositionColor(Cell cell) {

        String result;

        if (cell == null) {
            result = "/";
        } else {


            String composition = cell.getStringCellValue();

            if (isInterlayer(composition)) {
                result = parseInterlayer(composition);
            } else {
                result = parseSteel(composition);
            }
        }

        if (result.contains("|LOW-E")) {
            result = "YT" + "|" + "LOW-E";
        }

        return result;
    }

    private static String parseSteel(String composition) {
        return colorMap.getOrDefault(composition, "G");
    }

    private static String parseInterlayer(String composition) {
        String sep = "\\+";
        String chinessChar = "[\\\\u4E00-\\\\u9FA5]+";
        String[]  baseGlass = composition.split(sep);
        String s0, s1;

        for (String glass : baseGlass) {
            if (composition.contains("绿")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "G");
                s1 = baseGlass[1].replaceAll(chinessChar, "G");
                composition = s0 + "|" + s1;
            } else if (composition.contains("LOW-E") && composition.contains("灰")) {
                composition = "YT" + "|" + "LOW-E";
            } else if (composition.contains("LOW-E") && composition.contains("白")) {
                composition = "C" + "|" + "LOW-E";
            } else if (composition.contains("(外片)") || composition.contains("(内片)")) {
                s0 = baseGlass[0].replaceAll("(外片)", " ");
                s1 = baseGlass[1].replaceAll("(内片)", " ");
                composition = s0 + "|" + s1;
            } else if (composition.contains("白")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "C");
                s1 = baseGlass[1].replaceAll(chinessChar, "C");
                composition = s0 + "|" + s1;
            } else if (composition.contains("兰")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "B");
                s1 = baseGlass[1].replaceAll(chinessChar, "B");
                composition = s0 + "|" + s1;
            } else if (composition.contains("浅灰")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "YT");
                s1 = baseGlass[1].replaceAll(chinessChar, "YT");
                composition = s0 + "|" + s1;
            } else if (composition.contains("深灰")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "YP");
                s1 = baseGlass[1].replaceAll(chinessChar, "YP");
                composition = s0 + "|" + s1;
            } else if (composition.contains("灰")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "YT");
                s1 = baseGlass[1].replaceAll(chinessChar, "YT");
                composition = s0 + "|" + s1;
            } else if (composition.contains("LOW-E")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "");
                s1 = baseGlass[1].replaceAll(chinessChar, "");
                composition = s0 + "|" + s1;
            } else if (composition.contains("茶")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "Z");
                s1 = baseGlass[1].replaceAll(chinessChar, "Z");
                composition = s0 + "|" + s1;
            } else if (composition.contains("夹层")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "SG");
                s1 = baseGlass[1].replaceAll(chinessChar, "SG");
                composition = s0 + "|" + s1;
            } else if (composition.contains("*")) {
                s0 = baseGlass[0].replaceAll(chinessChar, "4.0C");
                s1 = baseGlass[1].replaceAll(chinessChar, "4.0C");
                composition = s0 + "|" + s1;
            } else if (composition.contains("�")) {
                composition = "/";
            } else {
                composition = baseGlass[0] + "|" + baseGlass[1];
            }
        }

        return composition;
    }

    private static boolean isInterlayer(String composition) {
        return composition.contains("+");
    }

    private static String generateProductType(Cell cell) {
        if (cell == null) {
            return "interlayer";
        }
       return cell.getStringCellValue().contains("钢化") ? "steel" : "interlayer";
    }

    private static List<String> parseFactoryColumn(Cell cell) {
        if (cell == null) {
            return Collections.emptyList();
        }

        Set<String> resultSet = new HashSet<>();
        String factoryString = cell.getStringCellValue().replace("厂", "");

        for(char c : digitMap.keySet()) {
            factoryString = factoryString.replaceAll(String.valueOf(c), String.valueOf(digitMap.get(c)));
        }

        for(char c : factoryString.toCharArray()) {
            resultSet.add(String.valueOf(c));
        }

        return new ArrayList<>(resultSet);

    }

    private static void output2File(FileOutputStream outputStream, Map<String, Integer> column2IndexMap, Row outputRow) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int colNum = column2IndexMap.keySet().size();

        // 循环读取行数据
        for (int colIdx = 0; colIdx < colNum; colIdx++) {
            Cell cell = outputRow.getCell(colIdx);
            if (colIdx == 0) {
                if (cell == null) {
                    outputStream.write("\n".getBytes());
                } else {
                    cell.setCellType(CellType.STRING);
                    String outVal = cell.getStringCellValue().replace(",", "");
                    outputStream.write(',');
                    outputStream.write(("\n" + outVal).getBytes(StandardCharsets.UTF_8));
                }
            } else if (cell == null) {
                outputStream.write(",".getBytes());
            } else if (colIdx == column2IndexMap.get("last_update_date")) {
//                System.out.println(cell.getStringCellValue());
                double val = (cell.getNumericCellValue() - 25570 + 2 / 3.0) * 24 * 3600 * 1000;
//                System.out.println(val);
                String timeStr = sdf.format(val);
                outputStream.write(',');
                outputStream.write(timeStr.getBytes());
            } else {
                cell.setCellType(CellType.STRING);
                String outCell = cell.getStringCellValue();
                outCell = outCell.replace(",", " ");
                outputStream.write(',');
                outputStream.write((outCell).getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    private static Double convertThickness(Cell cell) {
        if (cell == null) {
            return 20d;
        }
        return thicknessRangeMap.floorEntry(Double.valueOf(cell.getStringCellValue())).getValue();
    }

    public static void readExcel(String inputFile, String outputFile) {

        if (!fileExist(inputFile)) {
            System.out.println("文件不存在!");
        }


        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            Map<String, Integer> outputColumn2IndexMap = new LinkedHashMap<String, Integer>() {{
                put("product_id", 0);
                put("market_property", 1);
                put("property_original_text", 2);
                put("dxf_id", 3);
                put("length", 4);
                put("width", 5);
                put("thickness", 6);
                put("thickness_deviation", 7);
                put("cut_type", 8);
                put("composition", 9);
                put("area", 10);
                put("product_type", 11);
                put("last_update_date", 12);
                put("thickness_bias_lower", 13);
                put("thickness_bias_upper", 14);
                put("property", 15);
                put("direction_type", 16);
                put("thickness_orig", 17);
            }};

            outputStream.write((String.join(",", outputColumn2IndexMap.keySet())).getBytes());
            System.out.println("####### header: " + String.join(",", outputColumn2IndexMap.keySet()));


            Sheet sheet = getFirstSheet(inputFile);
            Sheet outSheet = new XSSFWorkbook().createSheet();
            setColumn2IndexMap(sheet);
            printSummary(sheet);

            int rowsOfSheet = sheet.getLastRowNum();

            // 行循环
            for (int inRowIdx = 1, outRowIdx = 1; inRowIdx <= rowsOfSheet; inRowIdx++, outRowIdx++) {
//                System.out.println(inRowIdx + " out");
                Row inputRow = sheet.getRow(inRowIdx);
                Row outputRow = outSheet.createRow(outRowIdx);

                if (inputRow == null) {
                    continue;
                }

                if (inputRow.getCell(indexOfCol("长")) == null
                        || inputRow.getCell(indexOfCol("宽")) == null
                        || inputRow.getCell(indexOfCol("厚")) == null)
                {
                    outputRow.setHeight((short) 0);
                    continue;
                }

                List<String> factoryUseCurrentProductList = parseFactoryColumn(inputRow.getCell(indexOfCol("工厂")));

                // 存在如 重慶xx廠這類非 1~9 廠。為了讓 product id 符合 product_factory_{n} 的格式，故手動將其塞入 "n"
                if (factoryUseCurrentProductList.isEmpty()) {
                    factoryUseCurrentProductList.add("n");
                }

                for(String factoryId : factoryUseCurrentProductList) {


//                    System.out.println(inRowIdx + "in");

                    outputRow.createCell(outputColumn2IndexMap.get("product_id")).setCellValue(inputRow.getCell(indexOfCol("产品")).getStringCellValue() + "_F" + factoryId);
                    outputRow.createCell(outputColumn2IndexMap.get("market_property")).setCellValue(generateMarketProperty(inputRow.getCell(indexOfCol("市场属性"))));
                    outputRow.createCell(outputColumn2IndexMap.get("property_original_text")).setCellValue(inputRow.getCell(indexOfCol("属性")).getStringCellValue());
                    outputRow.createCell(outputColumn2IndexMap.get("dxf_id")).setCellValue(generateDxfId(inputRow.getCell(indexOfCol("切割图"))));
                    outputRow.createCell(outputColumn2IndexMap.get("length")).setCellValue(inputRow.getCell(indexOfCol("长")).getStringCellValue());
                    outputRow.createCell(outputColumn2IndexMap.get("width")).setCellValue(inputRow.getCell(indexOfCol("宽")).getStringCellValue());
                    outputRow.createCell(outputColumn2IndexMap.get("thickness")).setCellValue(convertThickness(inputRow.getCell(indexOfCol("厚"))));
                    outputRow.createCell(outputColumn2IndexMap.get("thickness_orig")).setCellValue(inputRow.getCell(indexOfCol("厚")).getStringCellValue());
                    outputRow.createCell(outputColumn2IndexMap.get("thickness_deviation")).setCellValue(inputRow.getCell(indexOfCol("厚度偏差")).getStringCellValue());

//                    System.out.println(outputRow.getCell(outputColumn2IndexMap.get("thickness_orig")).getStringCellValue());

                    outputRow.createCell(outputColumn2IndexMap.get("area")).setCellValue(
                            BigDecimal.valueOf(Double.parseDouble(inputRow.getCell(indexOfCol("长")).getStringCellValue())).multiply(
                                    BigDecimal.valueOf(Double.parseDouble(inputRow.getCell(indexOfCol("宽")).getStringCellValue()))).divide(
                                    new BigDecimal(1000 * 1000), 4, BigDecimal.ROUND_FLOOR).doubleValue());
                    outputRow.createCell(outputColumn2IndexMap.get("property")).setCellValue(generateProperty(inputRow.getCell(indexOfCol("属性"))));

                    Pair<Double, Double> thicknessPair = generateThicknessDeviation(inputRow.getCell(indexOfCol("厚度偏差")), inRowIdx);
                    outputRow.createCell(outputColumn2IndexMap.get("thickness_bias_lower")).setCellValue(thicknessPair.getLeft());
                    outputRow.createCell(outputColumn2IndexMap.get("thickness_bias_upper")).setCellValue(thicknessPair.getRight());
                    outputRow.createCell(outputColumn2IndexMap.get("cut_type")).setCellValue(generateCutType(inputRow.getCell(indexOfCol("切割类型"))));
                    outputRow.createCell(outputColumn2IndexMap.get("composition")).setCellValue(generateCompositionColor(inputRow.getCell(indexOfCol("原片组成"))));
                    outputRow.createCell(outputColumn2IndexMap.get("product_type")).setCellValue(generateProductType(inputRow.getCell(indexOfCol("产品类型"))));
                    outputRow.createCell(outputColumn2IndexMap.get("last_update_date")).setCellValue(inputRow.getCell(indexOfCol("LAST_UPDATE_DATE")).getNumericCellValue());


                    output2File(outputStream, outputColumn2IndexMap, outputRow);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
