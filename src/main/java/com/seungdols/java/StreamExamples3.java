package com.seungdols.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @PACKAGE com.seungdols.java
 * @Author seungdols
 * @Date 2017. 10. 25.
 */
public class StreamExamples3 {

	public static void main(String[] args) {
		System.out.println("collect(toList()): " +
			Stream.of(1, 2, 3, 3,4, 5)
				.map(i -> i * 2)
				.map(i -> "#" + i)
				.collect(toList())
		);

		System.out.println("collect(toSet()): " +
			Stream.of(1, 2, 3, 3, 4, 5)
				.map(i -> i * 2)
				.map(i -> "#" + i)
				.collect(toSet())
		);


		System.out.println("collect(joining(\" | \")): " +
			Stream.of(1, 2, 3, 3, 4, 5)
				.map(i -> i * 2)
				.map(i -> "#" + i)
				.collect(joining(" | "))
		);


		System.out.println("collect(joining(\" | \" , \"[\", \"]\")): " +
			Stream.of(1, 2, 3, 3, 4, 5)
				.map(i -> i * 2)
				.map(i -> "#" + i)
				.collect(joining(" | ", "[", "]"))
		);

		System.out.println(".distinc().collect(joining(\" | \" , \"[\", \"]\")): " +
			Stream.of(1, 2, 3, 3, 4, 5)
				.map(i -> i * 2)
				.map(i -> "#" + i)
				.distinct()
				.collect(joining(" | ", "[", "]"))
		);

		//Auto Boxing and Auto Unboxing에 대해 공부하는 것도 좋아보인다.
		//Auto Boxing이 Integer.valueof로 하는데, 이 메소드가 caching을 한다.
		final Integer integer3 = 3;
		System.out.println(
			Stream.of(1, 2, 3, 4, 5)
		   		  .filter(i -> i == integer3) //사실은 object의 identity 비교기때문에 값이 안나와야 맞다. 그런데 값이 나오는 이유는 auto boxing에 대한 이유때문이다.
				  .findFirst()
		);

		System.out.println(
			Stream.of(1, 2, 3, 4, 5)
		   		  .filter(i -> i.equals(integer3))
				  .findFirst()
		);

		final Integer integer128 = 128;
		System.out.println(
			Stream.of(1, 2, 3, 4, 5 , 128)
		   		  .filter(i -> i == integer128)
				  .findFirst()
		);

		System.out.println(
			Stream.of(1, 2, 3, 4, 5, 128)
				.filter(i -> i.equals(integer128))
				.findFirst()
		);

		System.out.println("count() :" +
			Stream.of(1,2,3,4,5)
			      .filter(i -> i > integer3)
			      .count()
		);

		//외부 반복
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		for (Integer i : numbers) {
			System.out.println("i = " + i);
		}

		//내부 반복
		Stream.of(1, 2, 3, 4, 5)
			  .forEach(i -> System.out.println("i = " + i));
	}
}
