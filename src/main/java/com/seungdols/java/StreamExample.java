package com.seungdols.java;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @PACKAGE com.seungdols.java
 * @Author seungdols
 * @Date 2017. 10. 24.
 */
public class StreamExample {
	public static void main(String[] args) {

		IntStream.range(1, 10).forEach(i -> System.out.print(i + " ") );
		System.out.println();
		IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " ") );
		IntStream.iterate(1, i -> i + 1).forEach(i -> System.out.print(i + " "));
		//무한대 컬렉션 생성
		Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).forEach(i -> System.out.print(i + " "));
	}
}
