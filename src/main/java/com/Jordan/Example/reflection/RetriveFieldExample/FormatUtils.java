package com.Jordan.Example.reflection.RetriveFieldExample;


import org.apache.commons.math3.util.Precision;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>模組名稱：FormatUtils</p>
 * <p>模組功能：</p>
 * <p>創建時間：2020-05-08 = 周士堯(Frank Chou)</p>
 */
public class FormatUtils {

    public static List<Object> changeToDesiredFormatFromList(List<Object> objList){
        List<Object> newObject = new ArrayList<>();
        if(objList.size() > 0){
            for(Object obj : objList){
                String length = getter(obj, "Length").toString();
                String width = getter(obj, "Width").toString();
                String thickness = getter(obj, "Thickness").toString();
                String area = getter(obj, "Area").toString();
                String cutRate = getter(obj, "CutRate").toString();

                double areaDouble = Precision.round(Double.parseDouble(area)/(1000 * 1000), 4);
                double lengthDouble = Precision.round(Double.parseDouble(length)/10, 2);
                double widthDouble = Precision.round(Double.parseDouble(width)/10, 2);
                double thicknessDouble = Precision.round(Double.parseDouble(thickness)/10, 2);
                double cutRateDouble = Precision.round(Double.parseDouble(cutRate), 2);

//                obj = setter(obj, "Length", lengthDouble, double.class);
//                obj = setter(obj, "Width", widthDouble, double.class);
//                obj = setter(obj, "Thickness", thicknessDouble, double.class);
//                obj = setter(obj, "Area", areaDouble, double.class);
//                obj = setter(obj, "CutRate", areaDouble, double.class);
                newObject.add(obj);
            }
        }
        return newObject;
    }
    public static Object changeToDesiredFormat(Object obj) throws IllegalAccessException, InstantiationException {

        Class<?> c1 = obj.getClass();
        Object newObj = c1.newInstance();

        if(isObjectContainField(obj, "length"))  {
            String length = Objects.requireNonNull(getter(obj, "Length")).toString();
            double newLength = Precision.round(Double.parseDouble(length), 1);
            setter(obj, "Length", newLength, double.class);
        }

        if(isObjectContainField(obj, "width")) {
            String width = Objects.requireNonNull(getter(obj, "Width")).toString();
            double newWidth = Precision.round(Double.parseDouble(width), 1);
            setter(obj, "Width", newWidth, double.class);
        }

        if(isObjectContainField(obj, "thickness")) {
            String thickness = Objects.requireNonNull(getter(obj, "Thinkness")).toString();
            double newThickness = Precision.round(Double.parseDouble(thickness), 1);
            setter(obj, "Thickness", newThickness, double.class);
        }
        if(isObjectContainField(obj, "area")) {
            String area = Objects.requireNonNull(getter(obj, "Area")).toString();
            double newArea = Precision.round(Double.parseDouble(area), 4);
            setter(obj, "Area", newArea, Double.class);

        }
        if(isObjectContainField(obj, "cutRate")) {

            String cutRate = Objects.requireNonNull(getter(obj, "CutRate")).toString();
            double newCutRate = Precision.round(Double.parseDouble(cutRate), 2);
            setter(obj, "CutRate", newCutRate, Double.class);
        }

        return obj;
    }

    public static void setter(Object obj, String fieldName, Object value, Class<?> type) {
        try {
            Method met = obj.getClass().getMethod("set" + fieldName, type);
            met.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getter(Object obj, String fieldName) {
        try {
            Method met = obj.getClass().getMethod("get" + fieldName);
            return met.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isObjectContainField(Object object, String fieldName) {

        Class<?> objectClass = object.getClass();

        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }

}
