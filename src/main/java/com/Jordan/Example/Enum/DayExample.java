package com.Jordan.Example.Enum;

public class DayExample {
    private enum DAY {
        SUNDAY,
        MONDAY,
        SATURDAY
    }

    public enum DAYWITHASSIGNVALUE {
        SUNDAY("a"),
        MONDAY("b"),
        SATURDAY("c");

        private final String value;

        DAYWITHASSIGNVALUE(String i) {
            this.value = i;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static void main(String[] args) {
        // DAY
        for(DAY day: DAY.values()) {
            System.out.println(day.name() + " value: " + day.ordinal());
        }
        // DAYWITHASSIGNVALUE
        for(DAYWITHASSIGNVALUE daywithassignvalue: DAYWITHASSIGNVALUE.values()) {
            System.out.println(daywithassignvalue.name() + " value: " + daywithassignvalue.getValue());
        }
    }
}
