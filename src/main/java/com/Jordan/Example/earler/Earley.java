package com.Jordan.Example.earler;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class Earley {
    private Grammar grammar;
    private ArrayList<LinkedList<State>> stateSets;

    public Earley() {
        grammar = new Grammar();
        stateSets = new ArrayList<>();
    }

    // helper methods
    private boolean isNonTerminal(Character character) {
        return Character.isUpperCase(character) || character.equals('@');
    }

    // prints state set contents
	/*private void print() {
		for (int idx = 0; idx < stateSets.size(); ++idx) {
			System.out.println("------ State set " + idx + " -----------");
			for (State s : stateSets.get(idx)) {
				String rhs = Arrays.toString(s.prod.prodRhs).replaceAll(", ", "").substring(1, s.prod.prodRhs.length + 1);
				System.out.println(s.prod.prodHead + " -> " + rhs + ", " + s.rhsIdx + " ; " + s.prevSet);
			}
		}
	}*/

    // If production (P, j) is in S_i and x_{i+1}= current terminal, then add P with rhsIdx+1 to S_{i+1}
    private void scanner(State state, int currentIndex) {
        State newState = new State(state.production, state.rightHandStateIndex + 1, state.previousSet);
        System.out.println("scanner: " + new Gson().toJson(newState));
        if (!stateSets.get(currentIndex + 1).contains(newState)) {
            System.out.println("scanner !stateSets.get(currentIndex + 1).contains(newState)");
            stateSets.get(currentIndex + 1).add(newState);
        }
    }

    // We use the predictor from [2]; it nicely handles $\varepsilon$-productions
    // For [A -> alpha o E beta, i], it adds [E -> o gamma, j] for all productions in S_i
    // with E on the left-hand side
    private void predictor(State state, int currentIndex, Set<Character> nullableVars) {
        System.out.println("predictor() state: " + new Gson().toJson(state));
        System.out.println("predictor() currentIndex: " + currentIndex);
        System.out.println("predictor() nullableVars: " + nullableVars);
        Character character = state.production.productRightHandSide[state.rightHandStateIndex];
        System.out.println("predictor() character: " + new Gson().toJson(character));
        for (Production production : grammar.productions) {
            System.out.println("predictor production(): " + new Gson().toJson(production));
            if (production.productHead.equals(character)) {
                System.out.println("predictor() production.productHead.equals(character)");
                State newState = new State(production, 0, currentIndex);
                if (!stateSets.get(currentIndex).contains(newState)) {
                    System.out.println("!stateSets.get(currentIndex).contains(newState)");
                    stateSets.get(currentIndex).add(newState);
                }
            }
        }
        // Need this to handle $\varepsilon$-productions [2]
        if (nullableVars.contains(character)) { // B is nullable, i.e., B =>* eps
            System.out.println("predictor() nullableVars.contains(character)");
            State newState = new State(state.production, state.rightHandStateIndex + 1, state.previousSet);
            System.out.println("predictor() newState: " + new Gson().toJson(state));
            if (!stateSets.get(currentIndex).contains(newState)) {
                System.out.println("predictor() !stateSets.get(currentIndex).contains(newState)");
                stateSets.get(currentIndex).add(newState);
            }
        }
    }

    private void completer(State state, int currentIndex) {
        System.out.println("completer() state: " + new Gson().toJson(state));
        System.out.println("completer() currentIndex: " + new Gson().toJson(currentIndex));
        int j = state.previousSet;
        LinkedList<State> stateSet = stateSets.get(j);
        System.out.println("completer() stateSet: " + new Gson().toJson(stateSet));
        for (int i = 0; i < stateSet.size(); ++i) {
            State s = stateSet.get(i);
            System.out.println("completer() i: " + i + ", s: " + new Gson().toJson(s));
            if (s.rightHandStateIndex < s.production.productRightHandSide.length
                    && s.production.productRightHandSide[s.rightHandStateIndex].equals(state.production.productHead)) {
                State newState = new State(s.production, s.rightHandStateIndex + 1, s.previousSet);
                System.out.println("completer() newState: " + new Gson().toJson(newState));
                if (!stateSets.get(currentIndex).contains(newState)) {
                    System.out.println("completer() !stateSets.get(currentIndex).contains(newState)");
                    stateSets.get(currentIndex).add(newState);
                }
            }
        }
    }

    private void initialize(int n) {
        // add the initial state set S_0
        System.out.println("initialize()");
        Production production = new Production("@", "S");
        LinkedList<State> initState = new LinkedList<>();
        initState.add(new State(production, 0, 0));
        stateSets.add(initState);
        for (int i = 0; i < n; ++i) {
            stateSets.add(new LinkedList<>());
        }
        System.out.println("initialize() stateSets: " + new Gson().toJson(stateSets));
    }

    private void process(int i, Character a, Set<Character> nullableVars) {
        LinkedList<State> stateSet = stateSets.get(i);
        System.out.println("process() i: " + i + ", stateSet: " + new Gson().toJson(stateSet));
        int currentSize = 0;
        do {
            currentSize = stateSet.size();
            // iterate over the states from the current state set
            for (int j = 0; j < currentSize; ++j) {
                State state = stateSet.get(j);
                System.out.println("process() j: " + j + ", state: " + new Gson().toJson(state));
                // apply either scanner, predictor, or completer
                if (state.rightHandStateIndex == state.production.productRightHandSide.length) {
                    System.out.println("process() state.rightHandStateIndex == state.production.productRightHandSide.length: true");
                    completer(state, i);
                } else {
                    System.out.println("process state.rightHandStateIndex == state.production.productRightHandSide.length: false");
                    if (isNonTerminal(state.production.productRightHandSide[state.rightHandStateIndex])) {
                        System.out.println("process isNonTerminal: true");
                        predictor(state, i, nullableVars);
                    } else if (state.production.productRightHandSide[state.rightHandStateIndex].equals(a)) {
                        System.out.println("process isNonTerminal: false");
                        scanner(state, i);
                    } // else Nothing left to do
                }
            }
        } while (currentSize != stateSet.size());
    }

    public boolean solve(String[] grammar, String word) {
        // load the grammar
        for (String subGrammar : grammar) {
            // System.out.println(s);
            System.out.println("solve() subGrammar: " + subGrammar);
            Character productHead = subGrammar.charAt(0);
            System.out.println("solve() productHead: " + productHead);
            for (String rightHandState : subGrammar.split("->")[1].split("\\|")) {
                System.out.println("solve() rightHandState: " + rightHandState);
                this.grammar.addProduction(subGrammar, rightHandState);
            }
        }
        System.out.println("solve() this.grammar: " + new Gson().toJson(this.grammar));
        // compute nullable symbols
        Set<Character> nullableVars = this.grammar.getNullable();
        System.out.println("nullableVars: " + new Gson().toJson(nullableVars));
        // run the earley recognizer
        initialize(word.length());
        for (int i = 0; i < word.length(); ++i) {
            int crrSet = i + 1; // set index
            Character character = word.charAt(i);
            System.out.println("i:" + i + ", character: " + character);
            // Initialize the set S_{i+1} by applying the three operations to S_i until S_i stops changing
            process(i, character, nullableVars);
        }
        process(word.length(), '/', nullableVars); // character '/' is never in T

        State finalState = new State(new Production("@", "S"), 1, 0);
        System.out.println("finalState: " + new Gson().toJson(finalState));
        LinkedList<State> lastStateSet = stateSets.get(word.length());
        // print();
        for (State state : lastStateSet) {
            System.out.println("state: " + new Gson().toJson(state));
            if (state.equals(finalState)) {
                return true;
            }
        }
        return false;
    }
}
