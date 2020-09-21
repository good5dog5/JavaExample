package com.Jordan.Example.guavaExample;

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;

public class RangeMapExample {
    public static void main(String[] args) {
        RangeMap<Double, String> bmiMap = ImmutableRangeMap.<Double, String>builder()
                .put(Range.closedOpen(0d, 20d), "偏輕")
                .put(Range.closedOpen(20d, 25d), "正常")
                .put(Range.closedOpen(25d, 30d), "偏重")
                .put(Range.closedOpen(30d, 35d), "肥胖")
                .put(Range.atLeast(35d), "非常肥胖")
                .build();

        System.out.println(bmiMap.get(22d));
    }
}
