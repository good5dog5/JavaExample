package com.EffectiveJava.Chapter6.item37;

import com.EffectiveJava.Chapter6.item34.Planet;
import lombok.RequiredArgsConstructor;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class Plant {
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    @Override
    public  String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = {
                new Plant("Basil", LifeCycle.ANNUAL),
                new Plant("Carroway", LifeCycle.BIENNIAL),
                new Plant("Dill",     LifeCycle.ANNUAL),
                new Plant("Lavendar", LifeCycle.PERENNIAL),
                new Plant("Parsley",  LifeCycle.BIENNIAL),
                new Plant("Rosemary", LifeCycle.PERENNIAL)
        };

        // using an EnumMap to associate data with an enum (p.172)
        Map<Plant.LifeCycle, Set<Plant>> plantByLifeCycle =
                new EnumMap<>(Plant.LifeCycle.class);

        for(Plant.LifeCycle lc : Plant.LifeCycle.values()) {
//            System.out.println(lc);
            plantByLifeCycle.put(lc, new HashSet<>());
        }
        for(Plant p : garden) {
            plantByLifeCycle.get(p.lifeCycle).add(p);
        }
        System.out.println(plantByLifeCycle);


        // Stream-based
        System.out.println(Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle))
        );

        // using a stream and an EnumMap to associate data with an enum (p.173)
        System.out.println(Arrays.stream(garden)
                .collect(groupingBy(p -> p.lifeCycle,
                        ()  -> new EnumMap<>(LifeCycle.class), toSet())));
    }


}
