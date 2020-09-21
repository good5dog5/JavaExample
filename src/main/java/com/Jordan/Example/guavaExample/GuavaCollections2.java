package com.Jordan.Example.guavaExample;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GuavaCollections2 {

    private static Set<String> buildSetStrings() {
        final Set<String> strings = new HashSet<>();
        strings.add("Java");
        strings.add("Groovy");
        strings.add("Jython");
        strings.add("JRuby");
        strings.add("Python");
        strings.add("Ruby");
        strings.add("Perl");
        strings.add("C");
        strings.add("C++");
        strings.add("C#");
        strings.add("Pascal");
        strings.add("Fortran");
        strings.add("Cobol");
        strings.add("Scala");
        strings.add("Clojure");
        strings.add("Basic");
        strings.add("PHP");
        strings.add("Flex/ActionScript");
        strings.add("JOVIAL");
        return strings;
    }

    public static void demonFilter() {
        printHeader("Collections2.filter(Collection,Predicate): 'J' Languages");
        final Set<String> strings = buildSetStrings();
        System.out.println("\nOriginal Strings (pre-filter):\n\t" + strings);
        final Collection<String> filteredStrings = Collections2.filter(strings,
                Predicates.containsPattern("^J"));
        System.out.println("\nFiltered Strings:\n\t" + filteredStrings);
        System.out.println("\nOriginal Strings (post-filter):\n\t" + strings);

    }
    private static void printHeader(final String headerText) {
        System.out.println("\n==========================================================");
        System.out.println("== " + headerText);
        System.out.println("==========================================================");
    }

    public static void main(String[] args) {
        demonFilter();

    }
}
