package com.Jordan.Example.earler;

import java.util.Arrays;
import java.util.HashSet;

public class EarleyExample {

    public static void main(String [] args) {
        /* Test our implementation of the Early parser. */
        // wordInGrammar("S->aA|Aa|aAAA|AAAABBBABABABA;A->aA|bA|~;B->bbbA|ABA|A", "aaa");
        // wordInGrammar("S->S+M|M;M->M*T|T;T->a|b|c|d", "a++b*c");
        System.out.println("All palindromic binary strings of length 5:");
        StringGenerator stringGenerator = new StringGenerator(new HashSet<>(Arrays.asList('0', '1')), 5);
        for (String word : stringGenerator) {
            System.out.println("======================================================");
            System.out.println("word: " + word);
            if (wordInGrammar("S->0S0|1S1|0|1|~", word)) {
                System.out.println(word);
            }
        }
    }

    public static boolean wordInGrammar(String grammar, String word) {
        Earley earley = new Earley();
        return earley.solve(grammar.split(";"), word);
    }
}
