package com.seungdols.java.stream4;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @PACKAGE com.seungdols.java.stream4
 * @AUTHOR seungdols
 * @DATE 2017. 11. 4.
 */
public class StreamExamples4 {

	public static void main(String[] args) {

		final List<Product> products = Arrays.asList(
				new Product(1L, "A", new BigDecimal("100.50")),
				new Product(2L, "B", new BigDecimal("23.00")),
				new Product(3L, "C", new BigDecimal("31.45")),
				new Product(4L, "D", new BigDecimal("80.20")),
				new Product(5L, "E", new BigDecimal("7.50")),
				new Product(6L, "F", new BigDecimal("20.10"))
		);

		BigDecimal bigDecimal = new BigDecimal("30");
		System.out.println("Product.price() >= 30" +
				products.stream()
								.filter(product -> product.getPrice().compareTo(bigDecimal) >= 0)
								.collect(toList())
		);
		System.out.println("Product.price() >= 30 (with joining)" +
				products.stream()
								.filter(product -> product.getPrice().compareTo(bigDecimal) >= 0)
								.map(product -> product.toString())
								.collect(joining("\n"))
		);

		System.out.println("Products total Count (with reduce): " +
				products.stream()
								.map(product -> product.getPrice())
								.reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
		);

		System.out.println("Total Count of Product.price() >= 30: " +
				products.stream()
								.filter(product -> product.getPrice().compareTo(bigDecimal) >= 0)
								.map(product -> product.getPrice())
								.reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))
		);

		System.out.println("Count of Product.price() >= 30: " +
				products.stream()
								.filter(product -> product.getPrice().compareTo(bigDecimal) >= 0)
								.count()
		);

		final OrderedItem item1 = new OrderedItem(1L, products.get(0), 1);
		final OrderedItem item2 = new OrderedItem(2L, products.get(2), 3);
		final OrderedItem item3 = new OrderedItem(3L, products.get(5), 5);

		final Order order = new Order(1L, Arrays.asList(item1, item2, item3));

		System.out.println("Order total Price: " + order.totalPrice());


	}

}


@Data
@AllArgsConstructor
class Product {

	private Long id;
	private String name;
	private BigDecimal price;
}

@Data
@AllArgsConstructor
class OrderedItem {

	private Long id;
	private Product product;
	private int quantity;

	public BigDecimal getTotalPrice() {
		return product.getPrice().multiply(new BigDecimal(quantity));
	}
}

@Data
@AllArgsConstructor
class Order {

	private Long id;
	private List<OrderedItem> items;

	public BigDecimal totalPrice() {
		return items.stream()
								.map(item -> item.getTotalPrice())
								.reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2));
	}
}