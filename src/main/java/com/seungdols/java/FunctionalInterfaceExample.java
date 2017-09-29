package com.seungdols.java;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @PACKAGE com.seungdols.java
 * @Author seungdols
 * @Date 2017. 9. 14.
 */
public class FunctionalInterfaceExample {
	public static void main(String[] args) {

		Function<String, Integer> toInt = new Function<String, Integer>() {
			@Override
			public Integer apply(String value) {
				return Integer.parseInt(value);
			}
		};

		final Integer number = toInt.apply("100");
		System.out.println(number);

		Function<String, Integer> toIntFP = value -> (Integer.parseInt(value));
		final Integer number2 = toIntFP.apply("200");
		System.out.println(number2);

		/**
		 * identity function은 같은 타입을 그대로 리턴 하는 function
		 * {@link Function.identity}
		 */
		final Function<Integer, Integer> identity = Function.identity();
		System.out.println(identity.apply(100));

		/**
		 * Consumer는 입력을 받아서 리턴이 없는 Function
		 * {@link Consumer}
		 */
		final Consumer<String> print = new Consumer<String>() {
			@Override
			public void accept(String value) {
				System.out.println(value);
			}
		};

		print.accept("Hello");

		final Consumer<String> printFP = (value) -> System.out.println(value);
		printFP.accept("Hello");

		Predicate<Integer> isPositive = i -> i > 0;

		System.out.println(isPositive.test(1));
		System.out.println(isPositive.test(0));
		System.out.println(isPositive.test(-1));
		System.out.println(isPositive.test(5));

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, -5, -3, -1, 0);

		List<Integer> positiveNumbers = new ArrayList<>();
		for (Integer num : numbers) {
			if (isPositive.test(num)) { //isPositive 부분만 변형해주면, 다양하게 작업 할 수 있다
				positiveNumbers.add(num);
			}
		}
		System.out.println(Arrays.deepToString(positiveNumbers.toArray()));

		List<Integer> test = isPositiveFilter(numbers, i -> i < 5);
		System.out.println(Arrays.deepToString(test.toArray()));

		//Lazy Evaluation을 이용해 성능을 극대화 할 수 있음.
		final Supplier<String> helloSupplier = () -> "Hello";
		System.out.println(helloSupplier.get());

		//		Long start = System.currentTimeMillis();
		//		printIfValidIndex(0, () -> getVeryExpensiveValue());
		//		printIfValidIndex(0, () -> getVeryExpensiveValue());
		//		printIfValidIndex(-1, () -> getVeryExpensiveValue());
		//		printIfValidIndex(-2, () -> getVeryExpensiveValue());
		//		System.out.println("It took " + (System.currentTimeMillis() - start)/1000 + " seconds");

		println(1, 2, 3, (i1, i2, i3) -> String.valueOf(i1 + i2 + i3));
		println("Area is ", 12, 20, (message, length, width) -> String.valueOf(message + (length * width)));
		println(1L, "seungdols", "seungdols0822@gmail.com", (id, name, email) -> "User info : ID=" + id + ",name=" + name + ",email=" + email);

		BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
		System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

		final InvalidFunctionalInterface invalidFunctionalInterface = new InvalidFunctionalInterface() {
			@Override
			public <T> String mkString(T value) {
				return value.toString();
			}
		};

		System.out.println("anonymous class : " + invalidFunctionalInterface.mkString(123));

		// target method가 제네릭이기 때문에 사용할 수 없음.
//		final InvalidFunctionalInterface invalidFunctionalInterfaceForlambda = value -> value.toString();


	}

	// 엄청 무거운 연산을 하는 메소드라 가정
	private static String getVeryExpensiveValue() {

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "seungdols";
	}

	private static void printIfValidIndex(int number, Supplier<String> value) {
		if (number >= 0) {
			System.out.println("The value is " + value.get() + ".");
		} else {
			System.out.println("Invalid.");
		}

	}

	public static <T> List<T> isPositiveFilter(List<T> list, Predicate<T> filter) {
		List<T> result = new ArrayList<>();
		for (T t : list) {
			if (filter.test(t)) {
				result.add(t);
			}
		}

		return result;
	}

	public static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, Funtionc3<T1, T2, T3, String> function) {
		System.out.println(function.apply(t1, t2, t3));
	}

}

@FunctionalInterface
interface Funtionc3<T1, T2, T3, R> {
	R apply(T1 t1, T2 t2, T3 t3);
}

@FunctionalInterface
interface BigDecimalToCurrency {
	String toCurrency(BigDecimal value);
}

/*
	Method자체에 제네릭 타입이 들어가면 FunctionalInterface가 제대로 동작하지 않는다
 */
@FunctionalInterface
interface InvalidFunctionalInterface{
	<T> String mkString(T value);
}


