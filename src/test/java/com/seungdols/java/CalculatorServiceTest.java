package com.seungdols.java;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * @PACKAGE com.seungdols.java
 * @Author seungdols
 * @Date 2017. 9. 14.
 */
public class CalculatorServiceTest {
	@Test
	public void calculateAddition() throws Exception {
		Calculation calculation = new Addition();

		final int actual = calculation.calculate(1, 1);
		assertThat(actual).isEqualTo(2);
	}
	@Test
	public void fpCalculateAddition() throws Exception {
		Calculation calculation = (a, b) -> (a + b);

		final int actual = calculation.calculate(1, 1);
		assertThat(actual).isEqualTo(2);
	}
	@Test
	public void calculateSubstaract() throws Exception {
		Calculation calculation = new Substract();

		final int actual = calculation.calculate(1, 1);
		assertThat(actual).isEqualTo(0);
	}
	@Test
	public void calculateMultiplication() throws Exception {
		Calculation calculation = new Multiplication();

		final int actual = calculation.calculate(1, 1);
		assertThat(actual).isEqualTo(1);
	}
	@Test
	public void calculateDivision() throws Exception {
		Calculation calculation = new Division();

		final int actual = calculation.calculate(1, 0);
		assertThat(actual).isEqualTo(0);
	}

}