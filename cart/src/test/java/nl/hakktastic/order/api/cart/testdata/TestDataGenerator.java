package nl.hakktastic.order.api.cart.testdata;

import nl.hakktastic.order.api.cart.entity.CartEntity;
import nl.hakktastic.order.api.cart.entity.CartItemEntity;

import java.util.List;

/**
 * Helper class for generating test data.
 *
 */
public final class TestDataGenerator {

    /**
     * Get valid cart entity.
     *
     * @return Creates and returns a {@link CartEntity}
     */
    public static CartEntity getValidCartEntity(){

        var cartItem1 = getValidCartItemEntity(1,1);
        var cartItem2 = getValidCartItemEntity(2,1);
        var cartItem3 = getValidCartItemEntity(3,1);

        return CartEntity
                .builder()
                .cartItemList(List.of(cartItem1,cartItem2,cartItem3))
                .build();
    }

    /**
     * Get valid cart item entity.
     *
     * @return Creates and returns a {@link CartItemEntity}
     */
    public static CartItemEntity getValidCartItemEntity(int productId, int quantity){

        return CartItemEntity
                .builder()
                .productId(productId)
                .quantity(quantity)
                .build();
    }
}
