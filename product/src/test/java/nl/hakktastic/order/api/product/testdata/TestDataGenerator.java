package nl.hakktastic.order.api.product.testdata;

import nl.hakktastic.order.api.product.entity.ProductEntity;

import java.math.BigDecimal;

/**
 * Helper class for generating test data.
 *
 */
public final class TestDataGenerator {

    public static final int PRODUCT_ID_INVALID = -1;

    public static ProductEntity getValidProductEntity(){

        return ProductEntity
                .builder()
                .productId(1)
                .name("shirt")
                .price(BigDecimal.valueOf(34.25))
                .build();
    }
}
