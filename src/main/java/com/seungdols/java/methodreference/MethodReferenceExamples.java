package com.seungdols.java.methodreference;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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

        System.out.println("\n=============================");
        System.out.println("methodReference03 call");
        methodReference03();


        //Lambda Expression
        final Function<Integer, String> fl = getDoubleThenToStringUsingLambdaExpression();
        final String retNumberLambda = fl.apply(3);

        System.out.println("retNumberLambda is result "+ retNumberLambda);

        //Method Reference
        final Function<Integer, String> fm = getDoubleThenToStringUsingMethodReference();
        final String retNumberMethodRef  = fm.apply(3);
        System.out.println("retNumberMethodRef is result " + retNumberMethodRef);

        System.out.println("\n=============================");

        //Using Lambda Expression
        final List<Function<Integer, String>> fsL = Arrays.asList(number -> String.valueOf(number * 2));
        for (final Function<Integer, String> function : fsL) {
            final String result = function.apply(3);
            System.out.println("The result is " + result);
        }

        //Method Reference
        final List<Function<Integer, String>> fsM = Arrays.asList(MethodReferenceExamples::doubleThenToString);
        for (final Function<Integer, String> function : fsM) {
            final String result = function.apply(3);
            System.out.println("The result is " + result);
        }

        //Using Lambda Expression
        final Function<Integer, String> fl2 = number -> String.valueOf(number * 2);
        final String lambda = fl2.apply(5);
        System.out.println("lambda is result : "+ lambda);

        //Method Reference
        final Function<Integer, String> fm2 = MethodReferenceExamples::doubleThenToString;
        final String method  = fm2.apply(5);
        System.out.println("methodRet is result : " + method);

        final List<Function<Integer, String>> both  = Arrays.asList(number -> String.valueOf(number * 2),MethodReferenceExamples::doubleThenToString);
        for (final Function<Integer, String> function : both) {
            final String retApply = function.apply(7);
            System.out.println("both, retApply is result : " + retApply);
        }

    }



    private static Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceExamples::doubleThenToString;
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambdaExpression() {
        return number -> String.valueOf(number * 2);
    }


    public static void methodReference03() {

        //Lambda Expresiion
        System.out.println(
            testFirstClassFunction1(3, i -> String.valueOf(i * 2))
        );

        //Method Reference
        System.out.println(
            testFirstClassFunction1(3, MethodReferenceExamples::doubleThenToString)
        );

    }

    private static String doubleThenToString(int number) {
        return String.valueOf(number * 2);
    }


    private static String testFirstClassFunction1(int number, Function<Integer, String> f) {
        return "The result is " + f.apply(number);
    }


}

class BigDecimalUtils {

    public static int compareTo(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2);
    }

}