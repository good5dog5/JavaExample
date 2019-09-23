package com.shark.example;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] argv) {
        List<String> stringList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count = stringList.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字串數量： " + count);

        count = stringList.stream().filter(string -> string.length() == 3).count();
        System.out.println("字串長度3數量： " + count);

        List<String> filterList = stringList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("篩選後的列表：" + filterList);

        String merged = stringList.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合併字串：" + merged);

        List<Integer> integerList = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = integerList.stream().map(i -> i *i).distinct().collect(Collectors.toList());
        System.out.println("平方列表：" + squaresList);

        IntSummaryStatistics summaryStatistics = integerList.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("列表中最大的數：" + summaryStatistics.getMax());
        System.out.println("列表中最小的數：" + summaryStatistics.getMin());
        System.out.println("列表數總和：" + summaryStatistics.getSum());
        System.out.println("平均數：" + summaryStatistics.getAverage());

        count = stringList.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字串數量：" + count);
    }
}
