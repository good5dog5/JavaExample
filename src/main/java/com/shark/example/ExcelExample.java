package com.shark.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExample {

    private static final String FILE_PATH = "example.xlsx";


    public static void main(String[] argv) {
        createWorkBook();
        readWorkBook();
    }

    private static void createWorkBook() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet");
        String[] columns = {"number", "square"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
        int rowIndex = 1;
        for (int i = 0; i < 100; i++) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(String.valueOf(i));
            row.createCell(1).setCellValue(String.valueOf(i * i));
            rowIndex = rowIndex + 1;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readWorkBook() {
        try {
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
                    stringBuilder.append(row.getCell(j).getStringCellValue());
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
