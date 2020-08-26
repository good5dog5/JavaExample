package com.Jordan.Example.reflection.RetriveFieldExample;

import java.lang.reflect.Field;

public class retriveField {


    public String outPutCsvTitle() {
        return "groupId,sheetGlassId,quantity,width,length,dxfName,dxfDestination,plotDestination,outputDestination,algoId,thickness,glassColor,glassClass";
    }

    private static void printOutAllField() {
        Field[] allFields = SampleClass.class.getDeclaredFields();
        for(Field f : allFields) {
            System.out.println(f.toString());
        }

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        SampleClass sampleObj = new SampleClass();
        sampleObj.setWidth(9.999);
        sampleObj.setLength(8.888);
        sampleObj.setArea(7.77777777777);

        FormatUtils.changeToDesiredFormat(sampleObj);
    }
}

