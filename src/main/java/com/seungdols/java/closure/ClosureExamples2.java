package com.seungdols.java.closure;

/**
 * @PACKAGE com.seungdols.java.closure
 * @AUTHOR seungdols
 * @DATE 2017. 11. 6.
 */
public class ClosureExamples2 {

    private int number = 990;

    @Override
    public String toString() {
        return new StringBuilder().append("ClosureExamples2 : { ")
                                  .append("number=")
                                  .append(number)
                                  .append(" }")
                                  .toString();
    }

    public static <T> String toString(T value) {
        return "The Value is " + String.valueOf(value);
    }

    public static void main(String[] args) {

        new ClosureExamples2().testClosureRun3();

    }


    private void testClosureRun1() {
        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println(ClosureExamples2.this.number);
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(this.number));

    }
    private void testClosureRun2() {
        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("this.toString(): " + this.toString());
            }
        });

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("ClosureExamples2.this.toString(): " + ClosureExamples2.this.toString());
            }
        });

        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + this.toString()));
    }
    private void testClosureRun3() {
        int number = 100;

        System.out.println("toString(): " + toString());
        System.out.println("toString(\"Hello\"): " + toString("Hello"));

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
//                System.out.println("this.toString(): " + this.toString("Hello")); Error - method에 shadowing 현상이 발생함.
                System.out.println("this.toString(): " + this.toString());
            }
        });

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println("ClosureExamples2.this.toString(): " + ClosureExamples2.this.toString());
            }
        });

//        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + this.toString()));
        testClosure("Lambda Expression", () -> System.out.println("this.toString(): " + this.toString("Hello")));
    }

    private void testClosureRun4() {
        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
//                int number = 50; // no compile-time error
                System.out.println(ClosureExamples2.this.number);
            }
        });

        testClosure("Lambda Expression", () -> {
//            int number = 50; // compile-time error
            System.out.println(this.number);
        });
    }

    private static void testClosure(final String name, final Runnable runnable) {
        System.out.println("====================================");
        System.out.print(name + ": ");
        runnable.run();
        System.out.println("====================================");
    }

}
