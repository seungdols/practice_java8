package com.seungdols.java.higherorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @PACKAGE com.seungdols.java.higherorder
 * @AUTHOR seungdols
 * @DATE 2017. 11. 10.
 */
public class HigherOrderFunctionExamples {

    public static void main(String[] args) {
        final Function<Function<Integer, String>, String> f = g -> g.apply(10);
        System.out.println(
            f.apply(i -> "#" + i)
        );

        final Function<Integer, Function<Integer, Integer>> f2 = i -> (i2 -> i + i2);
        System.out.println(
            f2.apply(1)
              .apply(9)
        );
        final List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6);
        final List<String> stringNumber = mapper(numList, i -> "#" + i);

        System.out.println(stringNumber);

        final Function<Integer, Function<Integer, Function<Integer, Integer>>> f3 = i1 -> i2 -> i3 -> i1 + i2 + i3;

        System.out.println( "f3.apply(1).apply(2).apply(3) = " +
            f3.apply(1)
              .apply(2)
              .apply(3)
        );


    }

    static private <T, R> List<R> mapper(List<T> list, Function<T, R> mapper) {
        List<R> result = new ArrayList<>();

        for (final T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}
