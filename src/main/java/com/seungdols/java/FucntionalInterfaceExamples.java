package com.seungdols.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @PACKAGE com.seungdols.java
 * @Author seungdols
 * @Date 2017. 9. 29.
 */
public class FucntionalInterfaceExamples {

	public static void main(String[] args) {
		final List<Product> products = Arrays.asList(
			new Product(1L, "A", new BigDecimal("10.100")),
			new Product(2L, "B", new BigDecimal("60.100")),
			new Product(3L, "C", new BigDecimal("80.160")),
			new Product(4L, "D", new BigDecimal("40.100")),
			new Product(5L, "E", new BigDecimal("30.50")),
			new Product(6L, "F", new BigDecimal("20.10"))
		);

		List<Product> result = filter(products, product -> product.getPrice().compareTo(new BigDecimal("20")) >= 0);
		System.out.println(result);

		final List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);

		final List<DiscoutedProduct> discoutedProducts = map(expensiveProducts, product -> new DiscoutedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));

		System.out.println("expensiveProducts : " + expensiveProducts);
		System.out.println("discountedProducts : " + discoutedProducts);
	}

	private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		List<T> result = new ArrayList<>();
		for (final T t : list) {
			if (predicate.test(t)) {
				result.add(t);
			}
		}
		return result;
	}

	private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
		List<R> result = new ArrayList<>();
		for (final T t : list) {
			result.add(function.apply(t));
		}
		return result;
	}

}

class Product {
	private Long id;
	private String name;
	private BigDecimal price;

	public Product(Long id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}

@ToString(callSuper = true)
class DiscoutedProduct extends Product {
	public DiscoutedProduct(Long id, String name, BigDecimal price) {
		super(id, name, price);
	}
}