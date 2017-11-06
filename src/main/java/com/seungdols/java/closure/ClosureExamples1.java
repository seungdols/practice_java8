package com.seungdols.java.closure;

/**
 * @PACKAGE com.seungdols.java.closure
 * @AUTHOR seungdols
 * @DATE 2017. 11. 6.
 */
public class ClosureExamples1 {

    public static void main(String[] args) {

        /*
            실제로 number에 접근하려면, 기존에는 원소가 1개인 상수 배열을 만들어서 접근하곤했다.
            막상, Anonymous class 내부에서 number에 접근해 값을 변경하고자 해도, 접근이 안된다. (final 키워드가 없어도 접근 불가)
            그래서 effectively final처럼 쓰던지, final 키워드를 명시해주어야 하고, lambda expression에서도 똑같은 제약을 갖는다.
         */

        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(number));

    }

    private static void testClosure(final String name, final Runnable runnable) {
        System.out.println("====================================");
        System.out.print(name + ": ");
        runnable.run();
        System.out.println("====================================");
    }

}
