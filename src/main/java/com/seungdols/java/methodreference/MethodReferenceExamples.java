package com.seungdols.java.methodreference;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @PACKAGE com.seungdols.java.methodreference
 * @AUTHOR seungdols
 * @DATE 2017. 11. 11.
 */
public class MethodReferenceExamples {

    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
              .forEach(System.out::println);

        System.out.println(
            Arrays.asList(new BigDecimal("10"), new BigDecimal("23.94"), new BigDecimal("16"))
                  .stream()
//                 .sorted()//
//                 .sorted(BigDecimalUtils::compareTo)
                  .sorted(BigDecimal::compareTo)
                  .collect(toList())
        );

        final String targetString = "c";
        System.out.println(
            Arrays.asList("a", "b", "c", "d")
                  .stream()
                  .anyMatch("c"::equals)
//                  .anyMatch(targetString::equals)
//                  .anyMatch(s -> s.equals("c"))
        );

    }


}

class BigDecimalUtils {

    public static int compareTo(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2);
    }

}