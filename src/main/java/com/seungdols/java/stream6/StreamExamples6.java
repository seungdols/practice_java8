package com.seungdols.java.stream6;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @PACKAGE com.seungdols.java.stream6
 * @AUTHOR seungdols
 * @DATE 2017. 11. 5.
 */
public class StreamExamples6 {

    private static final String[] priceString = {"1.0", "100.99", "35.75", "21.30", "88.00"};
    private static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("21"), new BigDecimal("25")};

    private static final Random random = new Random(123);
    private static final Random targetPriceRandom = new Random(111);

    private static final List<Product> products;

    static {
        final int length = 8_000_000;
        final Product[] list = new Product[length];

        for (int i = 1; i <= length; i++) {
            list[i - 1] = new Product((long) i, "Product" + i,
                new BigDecimal(priceString[random.nextInt(5)]));
        }

        products = Arrays.asList(list);
    }

    private static BigDecimal imperativeSum(final List<Product> products, final Predicate<Product> predicate) {
        BigDecimal sum = BigDecimal.ZERO;
        for (final Product product : products) {
            if (predicate.test(product)) {
                sum = sum.add(product.getPrice());
            }
        }
        return sum;
    }

    //Stream은 한 번 생성되고, 소멸되면 재사용 불가능함. 아래처럼 하면 좋지않음.
    private static BigDecimal streamSum(final Stream<Product> stream, final Predicate<Product> predicate) {
        return stream.filter(predicate)
                     .map(Product::getPrice)
                     .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void imperativeSumTest(BigDecimal targetPrice) {
        System.out.println("================== Imparative Test  ===================");
        final long start = System.currentTimeMillis();
        System.out.println("\nImparative Sum: " +
            imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("================== End of Test ========================");
    }

    private static void streamSumTest(BigDecimal targetPrice) {
        System.out.println("================== Stream Test  ===================");
        final long start = System.currentTimeMillis();
        System.out.println("\nStream Sum: " +
            streamSum(products.stream(), product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("================== End of Test ========================");
    }

    private static void parallelStreamSumTest(BigDecimal targetPrice) {
        System.out.println("============== Parallel Stream Test  ===================");
        final long start = System.currentTimeMillis();
        System.out.println("\nParallel Stream Sum: " +
            streamSum(products.parallelStream(), product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("================== End of Test ========================");
    }


    public static void main(String[] args) {
        BigDecimal price = new BigDecimal("20");
        imperativeSumTest(price);
        streamSumTest(price);
        parallelStreamSumTest(price);

        System.out.println("\n========= Ignore init Test =========");

        for (int i = 0; i < 5; i++) {
            BigDecimal targetPrice = targetPrices[targetPriceRandom.nextInt(3)];

            imperativeSumTest(targetPrice);
            streamSumTest(targetPrice);
            parallelStreamSumTest(targetPrice);
        }
    }

}

@Data
@AllArgsConstructor
class Product {

    private Long id;
    private String name;
    private BigDecimal price;

}


