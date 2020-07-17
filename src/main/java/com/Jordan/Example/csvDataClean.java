package com.Jordan.Example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class csvDataClean {

    public static void main(String[] args) {
        readExcel("/Users/jordan/Downloads/temp_process_info.xlsx");
    }

    public static void printSummary(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        System.out.println("工作表名称：" + sheetName);

        // 获取当前Sheet的总行数
        int rowsOfSheet = sheet.getLastRowNum();
        System.out.println("当前表格的总行数:" + rowsOfSheet);
    }

    public static void readExcel(String path) {

        File file = new File(path);

        if(!file.exists()) {
            System.out.println("文件不存在!");
        }
        FileInputStream fis = null;
        Workbook workBook = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (file.exists()) {

            try {
                InputStream is = new FileInputStream(file);
                workBook = WorkbookFactory.create(is);

                String filePath = "/Users/jordan/Downloads/20200522_process_info.csv";
                FileOutputStream out = new FileOutputStream(filePath);
                out.write(
                        ("product_id,market_property,property_original_text,dxf_id,length,width,thickness,thickness_deviation,cut_type,composition,"
                                + "area,product_type,last_update_date,thicknesw_bias_lower,thicknesw_bias_upper,property,direction_type\n")
                                .getBytes());

                for (int s = 0; s < 1; s++) {
                    out.write(("Sheet-" + Integer.toString(s)).getBytes());

                    Sheet sheet = workBook.getSheetAt(s);
                    printSummary(sheet);

                    int rowsOfSheet = sheet.getLastRowNum();
                    // 获取工作表名称
                    /*
                     * sheetAt.setColumnHidden((short) 4, true); sheetAt.setColumnHidden((short) 12,
                     * true);
                     */
                    // 新建列并定义每列的标题
                    /*
                     * Row row1 = sheetAt.getRow(0);
                     * row1.createCell(15).setCellValue("thicknesw_bias_lower");
                     * row1.createCell(16).setCellValue("thicknesw_bias_upper");
                     * row1.createCell(17).setCellValue("property");
                     * row1.getCell(0).setCellValue("product_id");
                     * row1.getCell(1).setCellValue("market_property");
                     * row1.getCell(2).setCellValue("property ");
                     * row1.getCell(3).setCellValue("dxf_id");
                     * row1.getCell(5).setCellValue("length");
                     * row1.getCell(6).setCellValue("width");
                     * row1.getCell(7).setCellValue("thickness");
                     * row1.getCell(8).setCellValue("thickness_deviation");
                     * row1.getCell(9).setCellValue("cut_type ");
                     * row1.getCell(10).setCellValue("composition");
                     * row1.getCell(11).setCellValue("area");
                     * row1.getCell(12).setCellValue("factory");
                     * row1.getCell(13).setCellValue("product_type");
                     * row1.getCell(14).setCellValue("last_update_date");
                     */

                    // 行循环
                    for (int r = 1; r <= rowsOfSheet; r++) {
                        Row row = sheet.getRow(r);
                        if (row == null) {

                            continue;
                        } else {
                            //for (int a =0;a<=row.getLastCellNum();a++) {
                            //if (row.getCell(a)!=null) {continue;}else{row.createCell(a).setCellValue("/");}}
                            if (row.getCell(5) == null) {
                                // sheetAt.removeRow(row);
                                row.setHeight((short) 0);
                                continue;
                            }

                            else if (row.getCell(6) == null) {
                                // sheetAt.removeRow(row);
                                row.setHeight((short) 0);
                                continue;
                            } else if (row.getCell(7) == null) {
                                // sheetAt.removeRow(row);
                                row.setHeight((short) 0);
                                continue;
                            }
                            // 市場属性字段处理
                            if (row.getCell(1) != null) {
                                // 后档-back_wind_shield
                                if (row.getCell(1).getStringCellValue().contains("配套")) {
                                    row.createCell(18).setCellValue("kit");
                                }else {
                                    row.createCell(18).setCellValue("accessory");
                                }
                            }
                            else {row.createCell(18).setCellValue("accessory");}
                            // 属性字段处理
                            if (row.getCell(2) != null) {
                                // 后档-back_wind_shield
                                if (row.getCell(2).getStringCellValue().contains("钢化挡风")) {
                                    row.createCell(17).setCellValue("back_wind_shield");
                                }
                                // 前档-front_wind_shield
                                else if (row.getCell(2).getStringCellValue().contains("挡风")) {
                                    row.createCell(17).setCellValue("front_wind_shield");
                                } else if (row.getCell(2).getStringCellValue().contains("镀膜")) {
                                    row.createCell(17).setCellValue("front_wind_shield");
                                } else if (row.getCell(2).getStringCellValue().contains("夹丝")) {
                                    row.createCell(17).setCellValue("sun_roof");
                                }
                                // 天窗-sun_roof
                                else if (row.getCell(2).getStringCellValue().contains("天窗")) {
                                    row.createCell(17).setCellValue("sun_roof");
                                }
                                // 边窗side_window
                                else {
                                    row.createCell(17).setCellValue("side_window");
                                }
                            }else {row.createCell(17).setCellValue("side_window");}


                            // 厚度偏差处理
                            if (row.getCell(8) != null) {
                                double max = 0;
                                double min = 0;
                                String target = row.getCell(8).getStringCellValue();
                                String str = "([-]|[+]|[±])?[0-9]+[.]?[0-9]+";
                                Pattern pattern = Pattern.compile(str);
                                Matcher matcher = pattern.matcher(target);
                                List<String> result = new ArrayList<String>();
                                while (matcher.find()) {
                                    result.add(matcher.group());
                                }
                                int lenth = result.size();
                                if (lenth == 2) {
                                    try {
                                        double a = Double.parseDouble(result.get(0));
                                        double b = Double.parseDouble(result.get(1));
                                        if (a > b) {
                                            max = a;
                                            min = b;
                                        } else {
                                            max = b;
                                            min = a;
                                        }
                                    } catch (Exception e) {
                                        System.out.println(r + "转换行数字" + result.get(0) + " ; " + result.get(1));
                                    }
                                } else if (lenth == 1) {
                                    String frist = result.get(0).substring(0, 1);
                                    if (frist.equals("±")) {
                                        try {
                                            max = Double
                                                    .parseDouble(result.get(0).substring(1, result.get(0).length()));
                                            min = Double.parseDouble(
                                                    "-" + result.get(0).substring(1, result.get(0).length()));
                                        } catch (Exception e) {
                                            System.out.println(r + "转换行数字" + result.get(0));
                                        }
                                    } else {
                                        try {
                                            max = Double.parseDouble(result.get(0));
                                            min = max;
                                        } catch (Exception e) {
                                            System.out.println(r + "转换行数字" + result.get(0));
                                        }
                                    }
                                } else if (lenth == 3) {
                                    try {
                                        double a = Double.parseDouble(result.get(0));
                                        double b = Double.parseDouble(result.get(1));
                                        double c = Double.parseDouble(result.get(2));
                                        max = min = a;
                                        if (b > max) {
                                            max = b;
                                        } else {
                                            min = b;
                                        }
                                        if (c > max) {
                                            min = max;
                                            max = c;
                                        } else {
                                            if (c < min) {
                                                min = c;
                                            }
                                        }
                                    } catch (Exception e) {
                                        System.out.println(r + "转换行数字出错" + result.get(0));
                                    }
                                } else {
                                    max = 0.1;
                                    min = -0.1;
                                }
                                row.createCell(15).setCellValue("" + min);
                                row.createCell(16).setCellValue("" + max);
                            }else {row.createCell(15).setCellValue("-0.2");
                                row.createCell(16).setCellValue("0.2");}
                            // 切割图处理

                            if (row.getCell(3) != null) {
                                if (row.getCell(3).getStringCellValue().contains("其")
                                        || row.getCell(3).getStringCellValue().contains("图")
                                        || row.getCell(3).getStringCellValue().contains("方形")
                                        || row.getCell(3).getStringCellValue().contains("标")
                                        || row.getCell(3).getStringCellValue().contains("大片")
                                        || row.getCell(3).getStringCellValue().contains("靠模")) {
                                    row.getCell(3).setCellValue("standard_image");
                                } else if (row.getCell(3).getStringCellValue().equals("0")) {
                                    row.getCell(3).setCellValue("standard_image");
                                }
                            } else {
                                row.createCell(3).setCellValue("standard_image");
                            }

                            // 切割类型的处理，转化成手切（hand）和机切（mechine）
                            if (row.getCell(9) != null && row.getCell(9).getStringCellValue().contains("手")) {
                                row.getCell(9).setCellValue("hand");
                            } else if (row.getCell(9) != null) {
                                row.getCell(9).setCellValue("machine");
                            } else {
                                row.createCell(9).setCellValue("machine");
                            }

                            // 原片组成数据处理

                            if (row.getCell(10) != null && row.getCell(10).getStringCellValue().contains("+")) {

                                String str = row.getCell(10).getStringCellValue();
                                String reg = "\\+";
                                String[] arr = str.split(reg);
                                String regex = "[\\u4E00-\\u9FA5]+";

                                for (int i = 0; i <= arr.length; i++) {
                                    if (row.getCell(10) != null) {
                                        if (row.getCell(10).getStringCellValue().contains("绿")) {
                                            String s0 = arr[0].replaceAll(regex, "G");
                                            String s1 = arr[1].replaceAll(regex, "G");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        } else if (row.getCell(10).getStringCellValue().contains("LOW-E")&&row.getCell(10).getStringCellValue().contains("灰")) {
                                            String s0 = arr[0].replaceAll("灰", "YT");
                                            String s1 = arr[1].replaceAll("在线LOW-E", "LOW-E");
                                            row.getCell(10).setCellValue( "YT" + "|" + "LOW-E");
                                        }
                                        else if (row.getCell(10).getStringCellValue().contains("LOW-E")&&row.getCell(10).getStringCellValue().contains("白")) {
                                            String s0 = arr[0].replaceAll("白", "C");
                                            String s1 = arr[1].replaceAll("在线LOW-E", "LOW-E");
                                            row.getCell(10).setCellValue("C" + "|" +"LOW-E");
                                        }
                                        else if (row.getCell(10).getStringCellValue().contains("(外片)")||row.getCell(10).getStringCellValue().contains("(内片)")) {
                                            String s0 = arr[0].replaceAll("(外片)", " ");
                                            String s1 = arr[1].replaceAll("(内片)", " ");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);}
                                        else if (row.getCell(10).getStringCellValue().contains("白")) {
                                            String s0 = arr[0].replaceAll(regex, "C");
                                            String s1 = arr[1].replaceAll(regex, "C");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        } else if (row.getCell(10).getStringCellValue().contains("兰")) {
                                            String s0 = arr[0].replaceAll(regex, "B");
                                            String s1 = arr[1].replaceAll(regex, "B");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        } else if (row.getCell(10).getStringCellValue().contains("浅灰")) {
                                            String s0 = arr[0].replaceAll(regex, "YT");
                                            String s1 = arr[1].replaceAll(regex, "YT");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        } else if (row.getCell(10).getStringCellValue().contains("深灰")) {
                                            String s0 = arr[0].replaceAll(regex, "YP");
                                            String s1 = arr[1].replaceAll(regex, "YP");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        } else if (row.getCell(10).getStringCellValue().contains("灰")) {
                                            String s0 = arr[0].replaceAll(regex, "YT");
                                            String s1 = arr[1].replaceAll(regex, "YT");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        }

                                        else if (row.getCell(10).getStringCellValue().contains("LOW-E")) {
                                            String s0 = arr[0].replaceAll(regex, "");
                                            String s1 = arr[1].replaceAll(regex, "");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        }	else if (row.getCell(10).getStringCellValue().contains("茶")) {
                                            String s0 = arr[0].replaceAll(regex, "Z");
                                            String s1 = arr[1].replaceAll(regex, "Z");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);

                                        } else if (row.getCell(10).getStringCellValue().contains("夹层")) {
                                            String s0 = arr[0].replaceAll(regex, "SG");
                                            String s1 = arr[1].replaceAll(regex, "SG");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        }

                                        else if (row.getCell(10).getStringCellValue().contains("*")) {
                                            String s0 = arr[0].replaceAll(regex, "4.0C");
                                            String s1 = arr[1].replaceAll(regex, "4.0C");
                                            row.getCell(10).setCellValue(s0 + "|" + s1);
                                        }

                                        else if (row.getCell(10).getStringCellValue().contains("�")) {

                                            row.getCell(10).setCellValue("/");}
                                        else {
                                            row.getCell(10).setCellValue(arr[0] + "|" + arr[1]);
                                        }
                                    }
                                }
                            }

                            else if (row.getCell(10) != null) {
                                int i = 10;
                                if (row.getCell(i).getStringCellValue().contains("深")) {
                                    row.getCell(i).setCellValue("YP");
                                } else if (row.getCell(i).getStringCellValue().contains("灰")) {
                                    row.getCell(i).setCellValue("YT");
                                } else if (row.getCell(i).getStringCellValue().contains("YJ")) {
                                    row.getCell(i).setCellValue("YJ");
                                } else if (row.getCell(i).getStringCellValue().contains("YT")) {
                                    row.getCell(i).setCellValue("YT");
                                } else if (row.getCell(i).getStringCellValue().contains("白")) {
                                    row.getCell(i).setCellValue("C");
                                } else if (row.getCell(i).getStringCellValue().contains("绿")) {
                                    row.getCell(i).setCellValue("G");
                                } else if (row.getCell(i).getStringCellValue().contains("兰")) {
                                    row.getCell(i).setCellValue("B");
                                } else if (row.getCell(i).getStringCellValue().contains("蓝")) {
                                    row.getCell(i).setCellValue("B");
                                } else if (row.getCell(i).getStringCellValue().contains("茶")) {
                                    row.getCell(i).setCellValue("Z");
                                }
                                else if (row.getCell(i).getStringCellValue().contains("LOW-E")) {
                                    row.getCell(i).setCellValue("C");
                                }else {
                                    row.createCell(10).setCellValue("G");
                                }
                            }else {
                                row.createCell(10).setCellValue("G");
                            }
                            if (row.getCell(10).getStringCellValue().contains("|LOW-E")) {

                                row.getCell(10).setCellValue( "YT" + "|" + "LOW-E");
                            }
                            // 产品类型处理，钢化（steel），夹层（interlayer）
                            if (row.getCell(13) != null && row.getCell(13).getStringCellValue().contains("钢化")) {
                                row.getCell(13).setCellValue("steel");
                            } else {
                                row.createCell(13).setCellValue("interlayer");
                            }

                        }
                        // 循环读取行数据

                        //for (Cell cell : row) {
                        for (int a =0;a<=18;a++) {
                            Cell cell = row.getCell(a);
                            if (a == 0) {
                                if (cell == null) {
                                    out.write("\n,".getBytes());
                                }else {cell.setCellType(CellType.STRING);
                                    String outCell = cell.getStringCellValue();
                                    outCell = outCell.replace(",", " ");
                                    out.write(',');
                                    out.write(("\n" + outCell).getBytes("utf-8"));
                                }
                            } else if (a == 4 || a == 12) {
                                continue;
                            } else if (cell == null) {
                                out.write(",".getBytes());
                            }
                            else if (a == 17) {
                                System.out.println(cell.getStringCellValue());
                                double val = (cell.getNumericCellValue() - 25570 + 2 / 3.0) * 24 * 3600 * 1000;
                                System.out.println(val);
                                String timeStr = sdf.format(val);
                                // System.out.println(timeStr);
                                out.write(',');
                                out.write(timeStr.getBytes());
                            }
                            else {cell.setCellType(CellType.STRING);
                                String outCell = cell.getStringCellValue();
                                outCell = outCell.replace(",", " ");
                                out.write(',');
                                out.write((outCell).getBytes("utf-8"));

                            }

                        }

                    }

                    out.close();

                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Finsh.........");
    }


}
