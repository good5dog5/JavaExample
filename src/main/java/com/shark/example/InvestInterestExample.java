package com.shark.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class InvestInterestExample {

    public static void main(String[] args) {
        int firstInvest = 10000;
        int monthInvest = 5000;
        double investRate = 1.10;
        for(int i = 0; i < 24; i ++) {
            int year = ((i + 1) / 12);
            if((i + 1) % 12 != 0) {
                year = year + 1;
            }
            System.out.println("i: " + i + ", year: " + year);
            BigDecimal profit = new BigDecimal(0);
            for(int y = 0; y < year; y ++) {
                int invest =  year - y;
                System.out.println("y: " + y + ", investYear: " + invest);
                int yearInitInvest = 0;
                if(y == 0) {
                    yearInitInvest = (i >= 11)? (firstInvest + 11 * monthInvest): (firstInvest + i * monthInvest);

                } else {
                    yearInitInvest = (i + 1 - y * 12) * monthInvest;
                }
                BigDecimal yearFinalInvest = new BigDecimal(
                        yearInitInvest  *  Math.pow(investRate, invest)).setScale(2, RoundingMode.HALF_UP);
                BigDecimal yearFinalProfit = yearFinalInvest.subtract(new BigDecimal(yearInitInvest)).setScale(2, RoundingMode.HALF_UP);
                profit = profit.add(yearFinalProfit).setScale(2, RoundingMode.HALF_UP);
                System.out.println("i: " + i
                        + ", yearInitInvest: " + yearInitInvest
                        + ", yearFinalInvest: " + yearFinalInvest.toString()
                        + ", yearFinalProfit: " + yearFinalProfit.toString());
            }
            System.out.println("profit: " + profit.toString());
        }
    }
}
