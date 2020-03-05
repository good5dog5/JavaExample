package com.shark.example.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

public class ReadExcelExample {

    private static final String FILE_PATH = "file/xls.xls";


    public static void main(String[] argv) {
        readWorkBook();
    }

    private static void readWorkBook() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Workbook workbook = WorkbookFactory.create(new File(FILE_PATH));
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            int i = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                Iterator<Cell> cellIterator = row.cellIterator();

                int j = 0;
                while (cellIterator.hasNext()) {
                    if (j != 0) {
                        stringBuilder.append(", ");
                    }
                    Cell cell = cellIterator.next();
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
                    j++;
                }
                stringBuilder.append("]");
                System.out.println("row: " + i + ", cell: " + stringBuilder.toString());
                i ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
