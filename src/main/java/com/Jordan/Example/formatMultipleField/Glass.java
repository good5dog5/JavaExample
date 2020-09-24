package com.Jordan.Example.formatMultipleField;

import lombok.Data;

import java.io.Serializable;

@Data
public class Glass implements Serializable {


    private Double cutRate;
    private Double width;
    private Double length;
    private Double thickness;

    private Double area;
}
