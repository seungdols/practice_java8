package com.seungdols.java;

import java.math.BigDecimal;

/**
 * @PACKAGE com.seungdols.java
 * @Author seungdols
 * @Date 2017. 9. 29.
 */
public class FucntionalInterfaceExamples {

	public static void main(String[] args) {

	}

}

class Proudct {
	private Long id;
	private String name;
	private BigDecimal price;

	public Proudct() {
	}

	public Proudct(Long id, String name, BigDecimal price) {
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

	@Override
	public String toString() {
		return "Proudct{" +
			"id=" + id +
			", name='" + name + '\'' +
			", price=" + price +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Proudct proudct = (Proudct)o;

		if (id != null ? !id.equals(proudct.id) : proudct.id != null)
			return false;
		if (name != null ? !name.equals(proudct.name) : proudct.name != null)
			return false;
		return price != null ? price.equals(proudct.price) : proudct.price == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		return result;
	}
}