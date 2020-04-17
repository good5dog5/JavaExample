package com.shark.example.earler;

class State {
    public Production production;
    public int rightHandStateIndex;
    public int previousSet;

    // Constructor
    public State(Production production, int rightHandStateIndex, int previousSet) {
        this.production = production; // production
        this.rightHandStateIndex = rightHandStateIndex; // position of the dot on the right-hand-side of the production
        this.previousSet = previousSet;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof State)) {
            return false;
        } else {
            State state = (State) object;
            // System.out.println("[DEBUG] " + prod.equals(s.prod));
            return rightHandStateIndex == state.rightHandStateIndex && previousSet == state.previousSet && production.equals(state.production);
        }
    }
}