package com.Jordan.Example.stream;
import java.lang.String;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.*;
import java.nio.file.*;
import java.io.IOException;

import com.Jordan.Example.stream.Person.Gender;
public class generalUsage {

    public static void main(String[] args) throws IOException {
        // 1. Integer Stream
        IntStream
                .range(1, 10)
                .forEach(System.out::print);
        System.out.println();

        // 2. Integer Stream with skip
        IntStream
                .range(1, 10)
                .skip(5)
                .forEach(System.out::println);
        System.out.println();

        // 3. Integer Stream with sum
        System.out.println(
                IntStream
                        .range(1, 5)
                        .sum());
        System.out.println();

        // 4. Stream.of, sorted and findFirst
        Stream.of("Ava", "Aneri", "Alberto")
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

        // 5. Stream from Array, sort, filter and print
        String[] names = {"Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah"};
        Arrays.stream(names)	// same as Stream.of(names)
                .filter(x -> x.startsWith("S"))
                .sorted()
                .forEach(System.out::println);

        // 6. average of squares of an int array
        Arrays.stream(new int[] {2, 4, 6, 8, 10})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);

        // 7. Stream from List, filter and print
        List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
        people
                .stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("a"))
                .forEach(System.out::println);

        // 8. Stream rows from text file, sort, filter, and print
        Stream<String> bands = Files.lines(Paths.get("file/bands.txt"));
        bands
                .sorted()
                .filter(x -> x.length() > 13)
                .forEach(System.out::println);
        bands.close();

        // 9. Stream rows from text file and save to List
        List<String> bands2 = Files.lines(Paths.get("file/bands.txt"))
                .filter(x -> x.contains("jit"))
                .collect(Collectors.toList());
        bands2.forEach(System.out::println);

        // 10. Stream rows from CSV file and count
        Stream<String> rows1 = Files.lines(Paths.get("file/data.txt"));
        int rowCount = (int)rows1
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .count();
        System.out.println(rowCount + " rows.");
        rows1.close();

        // 11. Stream rows from CSV file, parse data from rows
        Stream<String> rows2 = Files.lines(Paths.get("file/data.txt"));
        rows2
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
        rows2.close();

        // 12. Stream rows from CSV file, store fields in HashMap
        Stream<String> rows3 = Files.lines(Paths.get("file/data.txt"));
        Map<String, Integer> map;
        map = rows3
                .map(x -> x.split(","))
                .filter(x -> x.length == 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> Integer.parseInt(x[1])));
        rows3.close();
        for (String key : map.keySet()) {
            System.out.println(key + "  " + map.get(key));
        }

        // 13. Reduction - sum
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, Double::sum);
        System.out.println("Total = " + total);

        // 14. Reduction - summary statistics
        IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10)
                .summaryStatistics();
        System.out.println(summary);

        // 15. Joining
        joiningTest();
    }

    private void groupByTest() throws ParseException {

        List <Person> persons = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        persons.add(new Person(1, "Yashwant", dateFormat.parse("11/02/1982"), Person.Gender.MALE));
        persons.add(new Person(2, "Mahesh", dateFormat.parse("01/08/1981"), Gender.MALE));
        persons.add(new Person(3, "Vinay", dateFormat.parse("01/08/1981"), Gender.MALE));
        persons.add(new Person(4, "Dinesh", dateFormat.parse("01/08/1981"), Gender.MALE));
        persons.add(new Person(5, "Kapil", dateFormat.parse("01/02/1989"), Gender.MALE));
        persons.add(new Person(6, "Ganesh", dateFormat.parse("11/02/1982"), Gender.MALE));
        persons.add(new Person(7, "Nita", dateFormat.parse("01/08/1981"), Gender.FEMALE));
        persons.add(new Person(8, "Pallavi", dateFormat.parse("04/25/1987"), Gender.FEMALE));
        persons.add(new Person(9, "Mayuri", dateFormat.parse("01/08/1981"), Gender.FEMALE));
        persons.add(new Person(10, "Divya", dateFormat.parse("01/08/1981"), Gender.FEMALE));

//        List<List<Integer>> result = persons
//                .stream()
//                .collect(Collectors.groupingBy(Person::getGender,
//                        Collectors.groupingBy(Person::getBirthDate,
//                                Collectors.toList())))
//                .values()
//                .stream()
//                .map(aMap -> aMap
//                        .values()




    }

    private static void joiningTest() {
        //ref : http://blog.tonycube.com/2015/10/java-java8-3-stream.html
        List<Character> ch = Arrays.asList('G', 'e', 'e', 'k', 's');

        String chString = ch.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        System.out.print(chString);
    }

}
