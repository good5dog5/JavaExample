package com.EffectiveJava.Chapter6.item34;


// swtich on an enum to simulate a missing method (p. 167)
public class Inverse {
    public static Operation inverse(Operation op) {
        switch (op) {
            case PLUS: return Operation.MINUS;
            case MINUS: return Operation.PLUS;
            case TIMES: return Operation.DIVIDE;
            case DIVIDE: return Operation.TIMES;

            default: throw new AssertionError("unknown op: " + op);
        }
    }

    public static void main(String[] args) {
        double x = 12.0;
        double y = 13.0;
        for (Operation op : Operation.values()) {
            Operation invOp = inverse(op);
            System.out.printf("%f %s %f %s %f = %f%n",
                    x, op, y, invOp, y, invOp.apply(op.apply(x, y), y));
        }

    }

}
