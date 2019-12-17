package com.shark.example.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ReadExcelExample {

    private static final String FILE_PATH = "file/quote.xlsx";


    public static void main(String[] argv) {
        readWorkBook();
    }

    private static void readWorkBook() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Workbook workbook = WorkbookFactory.create(new File(FILE_PATH));
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("readWorkBook rowCount: " + rowCount);
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                int cellCount = row.getPhysicalNumberOfCells();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                for (int j = 0; j < cellCount; j++) {
                    if (j != 0) {
                        stringBuilder.append(", ");
                    }
                    Cell cell = row.getCell(j);
                    switch (cell.getCellTypeEnum()) {
                        case BOOLEAN:
                            stringBuilder.append(cell.getBooleanCellValue());
                            System.out.print(cell.getBooleanCellValue());
                            break;
                        case STRING:
                            stringBuilder.append(cell.getRichStringCellValue().toString());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                stringBuilder.append(simpleDateFormat.format(cell.getDateCellValue()));
                            } else {
                                stringBuilder.append(cell.getNumericCellValue());
                            }
                            break;
                        case FORMULA:
                            stringBuilder.append(cell.getCellFormula());
                            break;
                        case BLANK:
                        default:
                            break;
                    }
                }
                stringBuilder.append("]");
                System.out.println("row: " + i + ", cell: " + stringBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
