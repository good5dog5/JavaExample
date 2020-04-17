package com.shark.example.parser;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class ParserDouble {

    public static void main(String[] argv) {
        try {
            identify("0.0001242621932277");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void identify(String input) {
        try {
            String refinedInput = input.trim().replace(",", "");
            if (refinedInput.endsWith("%")) {
                refinedInput = refinedInput.replace("%", "").trim();
                BigDecimal parsedInput = BigDecimal.valueOf(Double.parseDouble(refinedInput)).divide(BigDecimal.valueOf(100), refinedInput.length() - 1, HALF_UP);
                refinedInput = String.valueOf(parsedInput);
            }
            int dotIndex = refinedInput.trim().indexOf(".");
            if (dotIndex > 0 && refinedInput.startsWith("0")) {

                Double value = Double.parseDouble(refinedInput);
                System.out.println(value);
            } else {
                System.out.println("dotIndex:" + dotIndex);
                System.out.println(refinedInput.startsWith("0"));
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
