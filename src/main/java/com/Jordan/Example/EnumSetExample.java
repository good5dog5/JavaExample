package com.Jordan.Example;

import java.util.EnumSet;

enum Gfg {
    CODE, LEARN, CONTRIBUTE, QUIZ, MCQ
};
public class EnumSetExample {

    public static void main(String[] args) {
        EnumSet<Gfg> set1, set2, set3, set4;

        set1 = EnumSet.of(Gfg.QUIZ,Gfg.CONTRIBUTE, Gfg.LEARN, Gfg.CODE);
        set2 = EnumSet.complementOf(set1);
        set3 = EnumSet.allOf(Gfg.class);
        set4 = EnumSet.range(Gfg.CODE, Gfg.CONTRIBUTE);


        System.out.println("set 1:" + set1);
        System.out.println("set 2:" + set2);
        System.out.println("set 3:" + set3);
        System.out.println("set 4:" + set4);
    }

}
