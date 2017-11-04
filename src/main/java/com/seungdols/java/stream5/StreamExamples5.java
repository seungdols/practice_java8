package com.seungdols.java.stream5;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.omg.CORBA.TIMEOUT;

/**
 * @PACKAGE com.seungdols.java.stream5
 * @AUTHOR seungdols
 * @DATE 2017. 11. 4.
 */
public class StreamExamples5 {

	public static void main(String[] args) {

		/*
		 Anonymous Class의 경우 class의 외부 variable에 접근하려면, final variable이어야함.
		 */
		final int[] sum1 = {0};
		IntStream.range(0, 100)
						 .forEach(i -> sum1[0] += i);

		System.out.println("sum: " + sum1[0]);

		final int[] sum2 = {0};
		IntStream.range(0, 100)
						 .parallel()
						 .forEach(i -> sum2[0] += i);

		System.out.println("parallel sum (with side effect): " + sum2[0]);

		System.out.println("stream sum (without side effect): " +
				IntStream.range(0, 100)
								 .sum()
		);

		System.out.println("parallel stream sum (without side effect): " +
				IntStream.range(0, 100)
								 .parallel()
								 .sum()
		);

		System.out.println("\n=======================");
		System.out.println("Parallel Stream ");
		final Long start1 = System.currentTimeMillis();
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
					.parallelStream()
					.map(i -> {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return i;
					})
					.forEach(i -> System.out.println(i));
		System.out.println("It's tooks : " + (System.currentTimeMillis() - start1) / 1000 + "s");

		System.out.println("\n=======================");
		System.out.println(" Stream ");
		final Long start2 = System.currentTimeMillis();
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
					.stream()
					.map(i -> {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return i;
					})
					.forEach(i -> System.out.println(i));
		System.out.println("It's tooks : " + (System.currentTimeMillis() - start2) / 1000 + "s");


//		System.out.println("\n=======================");
//		System.out.println("Parallel Stream (with parallelism, core: 8)");
//		//system 상의 core수를 조절
//		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7");
//		final Long start3 = System.currentTimeMillis();
//		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
//					.parallelStream()
//					.map(i -> {
//						try {
//							TimeUnit.SECONDS.sleep(1);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						return i;
//					})
//					.forEach(i -> System.out.println(i));
//		System.out.println("It's tooks : " + (System.currentTimeMillis() - start3) / 1000 + "s");


		System.out.println("\n=======================");
		System.out.println("Parallel Stream (with parallelism, core: 1)");
		//system 상의 core수를 조절
		/**
		 * System.setProperty로 parallelism을 설정할 경우 몇 가지 문제가 있을 수 있습니다.
		 * 1. JVM레벨 설정이므로 예기치 못한곳에서 원치 않는 코어 사용/비사용 문제가 발생할수 있습니다.
		 * 2. System.setProperty로 parallelism을 설정하기 전에 Parallel Stream 을 사용할 경우
		 *    setProperty로 설정한 값의 영향을 받지 않는것을 확인 했습니다. 아무래도 이미 생성된 ForkJoinPool을 내부적으로
		 *    그대로 사용하기 때문인것 같습니다.
		 * 3. 결국 JVM 실행시 옵션으로 설정하시는것을 권장합니다.
		 * ref. content of kevin github
		 **/

		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
		final Long start4 = System.currentTimeMillis();
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
					.parallelStream()
					.map(i -> {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return i;
					})
					.forEach(i -> System.out.println(i));
		System.out.println("It's tooks : " + (System.currentTimeMillis() - start4) / 1000 + "s");


	}

}
