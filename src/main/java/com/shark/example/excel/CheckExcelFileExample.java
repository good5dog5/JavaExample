package com.shark.example.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class CheckExcelFileExample {

    public static void main(String[] argv) {



        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream("file/building_materials.xlsx"));
            if(workbook == null) {
                System.out.println("building_materials.xlsx is not excel");
            } else {
                System.out.println("building_materials.xlsx is excel");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream("file/building_materials.numbers"));
            if(workbook == null) {
                System.out.println("building_materials.number is not excel");
            } else {
                System.out.println("building_materials.number is excel");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }


        try {
            FileMagic fm = FileMagic.valueOf(FileMagic.prepareToCheckMagic(new FileInputStream("file/building_materials.numbers")));
            switch(fm) {
                case OLE2:
                    System.out.println("OLE2");
                    break;
                case OOXML:
                    System.out.println("OOXML");
                    break;
                default:
                    System.out.println("IS NOT EXCEL");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileMagic fm = FileMagic.valueOf(FileMagic.prepareToCheckMagic(new FileInputStream("file/building_materials.xlsx")));
            switch(fm) {
                case OLE2:
                    System.out.println("OLE2");
                    break;
                case OOXML:
                    System.out.println("OOXML");
                    break;
                default:
                    System.out.println("IS NOT EXCEL");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
