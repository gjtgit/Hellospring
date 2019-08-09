package com.gjt.hellosb.util;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] arg) {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //filter,collect,limit,forEach
        List<String> filtered = list.stream()
                .filter(e -> !e.isEmpty())
                .collect(Collectors.toList());
        filtered.forEach(System.out::print);
        System.out.println(filtered);
        System.out.println(filtered.stream().limit(3).collect(Collectors.toList()));

        String filteredNew = list.stream()
                .filter(e -> !e.isEmpty())
                .collect(Collectors.joining(","));
        System.out.println(filteredNew);
        
        // map,sorted
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        squaresList.stream().sorted().forEach(System.out::println);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        
    }
    
    

}
