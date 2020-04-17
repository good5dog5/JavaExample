package com.shark.example.earler;

import java.util.Arrays;

class Production {
    public Character productHead; // head
    public Character[] productRightHandSide; // right hand side

    // Constructor
    public Production(Character productHead, Character[] productRightHandSide) {
        this.productHead = productHead;
        this.productRightHandSide = productRightHandSide;
    }

    public Production(String prodHead, String productRightHandSide) {
        // assert(prodHead.length() == 1);
        this.productHead = prodHead.charAt(0);
        this.productRightHandSide = toCharacterArray(productRightHandSide.toCharArray());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Production)) {
            return false;
        } else {
            Production p = (Production) o;
            return productHead.equals(p.productHead) && Arrays.equals(productRightHandSide, p.productRightHandSide);
        }
    }

    // Helper
    private static Character[] toCharacterArray(char[] array) {
        Character[] chArray = new Character[array.length];
        for (int i = 0; i < array.length; ++i) {
            chArray[i] = array[i];
        }
        return chArray;
    }
}

