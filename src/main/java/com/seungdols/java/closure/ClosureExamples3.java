package com.seungdols.java.closure;

/**
 * @PACKAGE com.seungdols.java.closure
 * @AUTHOR seungdols
 * @DATE 2017. 11. 7.
 */
public class ClosureExamples3 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int number = 100;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        };

        runnable.run();

        Runnable runnable1 = () -> System.out.println(number);
        runnable1.run();

    }

}
