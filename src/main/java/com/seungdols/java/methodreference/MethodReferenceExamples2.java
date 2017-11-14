package com.seungdols.java.methodreference;

import java.math.BigDecimal;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @PACKAGE com.seungdols.java.methodreference
 * @AUTHOR seungdols
 * @DATE 2017. 11. 15.
 */
public class MethodReferenceExamples2 {

    public static void main(String[] args) {

        Section section = new Section(2);

        Function<Integer, Section> sectionWithFactoryLambdaExpression = number -> new Section(number);
        Section sectionLambda = sectionWithFactoryLambdaExpression.apply(2);

        Function<Integer, Section> sectionWithFactoryMethodReference = Section::new;
        Section sectionMethodReference = sectionWithFactoryMethodReference.apply(2);
        System.out.println(section);
        System.out.println(sectionLambda);
        System.out.println(sectionMethodReference);

        final OldProduct oldProduct = new OldProduct(1L, "A", new BigDecimal("20"));
        System.out.println("oldProduct : " + oldProduct);

        OldProductFactory oldProductFactory = OldProduct::new;
        System.out.println("oldProductFactory : " + oldProductFactory.create(1L, "A", new BigDecimal("20")));

        final ProductA a = createProduct(1L, "A", new BigDecimal("30"), ProductA::new);
        final ProductB b = createProduct(1L, "A", new BigDecimal("30"), ProductB::new);

        System.out.println("=====================================");

        System.out.println(a);
        System.out.println(b);
    }

    private static <T extends Product> T createProduct(final Long id, final String name, final BigDecimal price, final ProductFactory<T> productFactory) {
        if (id == null || id < 1L) {
            throw new IllegalArgumentException("The is must be a positive Long.");
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name is not given.");
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The price is negative");
        }
        return productFactory.create(id, name, price);
    }

}

@FunctionalInterface
interface OldProductFactory {
    OldProduct create(Long id, String name, BigDecimal price);
}

@FunctionalInterface
interface ProductFactory<T extends Product> {
    T create(Long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
class Section {
    private int page;
}


@AllArgsConstructor
@Data
class OldProduct {
    private Long id;
    private String name;
    private BigDecimal price;
}

@AllArgsConstructor
@Data
abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}


class ProductA extends Product {

    ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "A=" + super.toString();
    }
}

class ProductB extends Product {

    ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "B=" + super.toString();
    }
}