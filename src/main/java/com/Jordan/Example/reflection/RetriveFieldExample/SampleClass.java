package com.Jordan.Example.reflection.RetriveFieldExample;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class SampleClass {
    //原片分組
    private String groupId;
    //原片編號
    private String sheetGlassId;
    //數量
    private Integer quantity;
    //寬
    private Double width;
    //長
    private Double length;
    //長

    private Double Area;
    //dxf檔名
    private String dxfName;
    //destination file location
    private String dxfDestination;
    //png 檔案路徑
    private String plotDestination;
    //輸出檔路徑
    private String outputDestination;
    //演算 id
    private String algoId = "algo_id";
    // 厚
    private Double thickness;
    // 原片玻璃顏色
    private String glassColor;
    // 原片玻璃等級
    private String glassClass;
}
