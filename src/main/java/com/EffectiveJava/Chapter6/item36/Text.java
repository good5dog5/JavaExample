package com.EffectiveJava.Chapter6.item36;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

// EnumSet - a modern replacement for bit fields (p.170)
public class Text {

    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // any set could be passed in, but EnumSet is clearly best
    public void applyStyles(Set<Style> styles) {
        System.out.printf("Applying styles %s to test %n",
                Objects.requireNonNull(styles));
    }

    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(EnumSet.of(Style.UNDERLINE, Style.BOLD));
    }

}
