package com.Jordan.Example.guavaExample;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;

public class JoinerExample {

    public static void joinStrings() {
        ImmutableSet<String> strings = ImmutableSet.of("A", "B" ,"C");
        String joined1 = Joiner.on(":").join(strings);
        String joined2 = Joiner.on(",").join(strings);

        System.out.println(joined1);
        System.out.println(joined2);
    }

    public static void splitStrings() {
        String s = "A:B:C";
        String[] parts = s.split(":");
        String backTogether = Joiner.on(":").join(parts);

        String gorbleString = ": A::: B : C :::";
        Iterable<String> gorbleParts = Splitter.on(":").omitEmptyStrings()
                .trimResults().splitToList(gorbleString);
        String gorbleBackTogether = Joiner.on(":").join(gorbleParts);
        System.out.println(gorbleBackTogether);

    }

    public static void main(String[] args) {
        joinStrings();
        splitStrings();
    }
}
