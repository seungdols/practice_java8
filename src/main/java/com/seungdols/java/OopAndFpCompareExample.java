package com.seungdols.java;

/**
 * @PACKAGE com.seungdols.java
 * @Author seungdols
 * @Date 2017. 9. 14.
 */
public class OopAndFpCompareExample {
	public static void main(String[] args) {
		final CalculatorService calculatorService = new CalculatorService(new Addition(), new Substract());
		final int additionResult = calculatorService.calculate(11, 1);
		System.out.println(additionResult);
		final int subtractResult = calculatorService.calculate(11, 1);
		System.out.println(subtractResult);

		final int multipleResult = calculatorService.calculate(11, 1);
		System.out.println(multipleResult);

		final int divisionResult = calculatorService.calculate(11, 1);
		System.out.println(divisionResult);

		final FpCalculatorService fpCalculatorService = new FpCalculatorService();
		System.out.println("addition: " + fpCalculatorService.calculate(new Addition(), 11, 4));
		System.out.println("subtraction: " + fpCalculatorService.calculate(new Substract(), 11, 1));
		System.out.println("multiplication:  " + fpCalculatorService.calculate(new Multiplication(), 11, 2));
		System.out.println("division: " + fpCalculatorService.calculate(new Division(), 12, 2));

		/*
		*
		* First Class Citizen 지원을 하기 위한 조건
		*
		* 1. method의 argument로 function을 전달 할 수 있어야함
		* 2. element에 function을 assign 할수 있어야 한다
		* 3. Data structure or variable에 assign 할 수 있어야 한다
		*
		* */
		System.out.println("function addition: " + fpCalculatorService.calculate((a, b) -> a + b, 11, 4));
		System.out.println("function subtraction: " + fpCalculatorService.calculate((a,b) -> a - b, 11, 1));
		System.out.println("function multiplication:  " + fpCalculatorService.calculate((a , b) -> a * b, 11, 2));
		System.out.println("function division: " + fpCalculatorService.calculate((a, b) -> a / b, 12, 2));

	}
}

interface Calculation {
	int calculate(int num1, int num2);
}

class Addition implements Calculation {
	@Override
	public int calculate(int num1, int num2) {
		return num1 + num2;
	}
}

class Substract implements Calculation {
	@Override
	public int calculate(int num1, int num2) {
		return num1 - num2;
	}
}

class Multiplication implements Calculation {
	@Override
	public int calculate(int num1, int num2) {
		return num1 * num2;
	}
}

class Division implements Calculation {
	@Override
	public int calculate(int num1, int num2) {
		return num1 / num2;
	}
}

class CalculatorService {

	private final Calculation calculation1;
	private final Calculation calculation2;

	public CalculatorService(Calculation calculation1, Calculation calculation2) {
		this.calculation1 = calculation1;
		this.calculation2 = calculation2;
	}

	public int calculate(int num1, int num2) {
		if (num1 > 10 && num2 < num1) {
			return calculation1.calculate(num1, num2);
		} else {
			throw new IllegalArgumentException("Invalid input num1: " + num1 + ", " + num2);
		}
	}

	public int compute(int num1, int num2) {
		if (num1 > 10 && num2 < num1) {
			return calculation2.calculate(num1, num2);
		} else {
			throw new IllegalArgumentException("Invalid input num1: " + num1 + ", " + num2);
		}
	}
}

class FpCalculatorService {
	public int calculate(Calculation calculation, int num1, int num2) {
		if (num1 > 10 && num2 < num1) {
			return calculation.calculate(num1, num2);
		} else {
			throw new IllegalArgumentException("Invalid input num1: " + num1 + ", " + num2);
		}
	}
}