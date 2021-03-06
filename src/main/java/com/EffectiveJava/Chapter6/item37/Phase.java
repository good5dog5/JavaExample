package com.EffectiveJava.Chapter6.item37;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

public enum Phase {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT(SOLID, LIQUID),
        FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS),
        CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS),
        DEPOSIT(GAS, SOLID);

        private final Phase from;
        private final Phase to;

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }
        // init the phase transition map
        private static final Map<Phase, Map<Phase, Transition>> m =
                Stream.of(values()).collect(groupingBy(t -> t.from, () -> new EnumMap<>(Phase.class),
                        toMap(t -> t.to, t->t,
                                (x,y) -> y, () -> new EnumMap<>(Phase.class))));

        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }

        public static void main(String[] args) {
            for (Phase src : Phase.values()) {
                for (Phase dst : Phase.values()) {
                    Transition transition = Transition.from(src, dst);

                    if (transition != null) {
                        System.out.printf("%s to %s : %s %n", src, dst, transition);
                    }
                }
            }
        }

    }
}
