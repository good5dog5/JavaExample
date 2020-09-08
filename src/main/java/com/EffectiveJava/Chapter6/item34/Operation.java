package com.EffectiveJava.Chapter6.item34;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
public enum  Operation {

    PLUS("+") {
        public double apply(double x, double y) { return x + y;}
    },
    MINUS("-") {
        public double apply(double x, double y) {return x - y;}
    },
    TIMES("*") {
        public double apply(double x, double y) {return x * y;}
    },
    DIVIDE("/") {
        public double apply(double x, double y) {return x / y;}
    };

    private final String symbol;

    Operation(String symbol) {this.symbol = symbol;}

    @Override
    public String toString() {return symbol;}

    public abstract double apply(double x, double y);

    // implementing a from string method on an enum type (p.164)

    private static final Map<String, Operation> stringToEnum =
            Stream.of(values()).collect(
                    toMap(Objects::toString, e -> e));


    // returns Operation for string, if any
    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    public static void main(String[] args) {
        double x = 12.0;
        double y = 13.0;

        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }
    }


}
