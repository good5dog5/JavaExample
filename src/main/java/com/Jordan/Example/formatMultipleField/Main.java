package com.Jordan.Example.formatMultipleField;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Glass glass = new Glass();
        glass.setWidth(3888.25934000);
        glass.setLength(277.25934000);
        glass.setThickness(111.25934000);
        glass.setArea(333.25934000);
        glass.setCutRate(99.25934000);

        System.out.println("Before: ");
        System.out.println(glass);
        FormatUtils.formatedTODesiredFormat(glass, Glass.class);
        System.out.println("After: ");
        System.out.println(glass);
    }
}
