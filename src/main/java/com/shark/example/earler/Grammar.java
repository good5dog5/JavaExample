package com.shark.example.earler;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

class Grammar {
    public ArrayList<Production> productions; // P

    // Constructor
    public Grammar() {
        productions = new ArrayList<>();
    }

    public Grammar(ArrayList<Production> productions) {
        this.productions = productions;
    }

    public boolean addProduction(Production prod) {
        return !productions.contains(prod) && productions.add(prod);
    }

    public boolean addProduction(Character prodHead, Character[] prodRhs) {
        return addProduction(new Production(prodHead, prodRhs));
    }

    public boolean addProduction(String prodHead, String prodRhs) {
        return addProduction(new Production(prodHead, prodRhs));
    }

    // Compute the set of nullable nonterminals: { A in V | A =>* eps }
    // I decided to go with a simple fixed-point algorithm
    public Set<Character> getNullable() {
        Set<Character> nullSet = new TreeSet<>();
        // find the ``base'' symbols --- all A in V such that A -> eps is in P
        for (Production production : productions) {
            if (production.productRightHandSide[0] == '~') { nullSet.add(production.productHead); }
        }
        if (nullSet.size() != 0) {
            boolean isNullable = true;
            int crrSize = nullSet.size();
            do {
                crrSize = nullSet.size();
                for (Production production : productions) {
                    isNullable = true;
                    for (Character character : production.productRightHandSide) {
                        if (character.isLowerCase(character) && character.isLetter(character) || !nullSet.contains(character)) {
                            isNullable = false; break;
                        }
                    }
                    if (isNullable) { nullSet.add(production.productHead); }
                }
            } while (crrSize != nullSet.size());
        }
        return nullSet;
    }
}

