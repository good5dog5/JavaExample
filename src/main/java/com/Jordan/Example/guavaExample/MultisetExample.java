package com.Jordan.Example.guavaExample;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.HashSet;
import java.util.Set;

public class MultisetExample {

    public static void main(String[] args) {
        withMultiset();
    }

    public static void withSet() {

        Set<String> aSet = new HashSet<>();
        aSet.add("Geeks");
        aSet.add("Geeks");

        System.out.println(aSet);
    }

    public static void withMultiset() {
        Multiset<String>  multiset = HashMultiset.create();
        multiset.add("Geeks");
        multiset.add("Geeks");
        multiset.add("Jordan");
        multiset.add("Jordan");

        System.out.println(multiset);
        System.out.println("Total size is : " + multiset.size());
        System.out.println("'Geeks''s count: " + multiset.count("Geeks"));
        System.out.println("'Jordan''s count: " + multiset.count("Jordan"));
    }
}
