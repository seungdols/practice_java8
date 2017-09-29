package com.seungdols.java;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * @PACKAGE com.seungdols.java
 * @Author seungdols
 * @Date 2017. 9. 13.
 */
public class WhyJava8 {
	public static void main(String[] args) {
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		final StringBuilder strigBuilder = new StringBuilder();

/*		int size = numbers.size();
		for(int i = 0; i < size; i++ ) {
			strigBuilder.append(numbers.get(i));
			if (i != size - 1) {
				strigBuilder.append(" : ");
			}
		}
		System.out.println(strigBuilder);*/

		for (Integer number : numbers) {
			strigBuilder.append(number).append(" : ");
		}

		if (strigBuilder.length() > 0) {
			strigBuilder.delete(strigBuilder.length() - 3, strigBuilder.length());
		}

		System.out.println(strigBuilder.toString());

		final String result = numbers.stream()
									 .map(String::valueOf)
									 .collect(joining(" : "));

		System.out.println(result);
	}
}
