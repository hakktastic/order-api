package nl.hakktastic.order.api.order.testdata;

import nl.hakktastic.order.api.order.entity.OrderEntity;
import nl.hakktastic.order.api.order.entity.OrderItemEntity;

import java.util.List;

/**
 * Helper class for generating test data.
 *
 */
public final class TestDataGenerator {

    /**
     * Get valid order entity.
     *
     * @return Creates and returns a {@link OrderEntity}
     */
    public static OrderEntity getValidOrderEntity(){

        var orderItem1 = getValidOrderItemEntity(1,1);
        var orderItem2 = getValidOrderItemEntity(2,1);
        var orderItem3 = getValidOrderItemEntity(3,1);

        return OrderEntity
                .builder()
                .customerName("mockCustomer")
                .mobileNr("1234567890")
                .orderItemList(List.of(orderItem1,orderItem2,orderItem3))
                .build();
    }

    /**
     * Get invalid order entity with customer name not set.
     *
     * @return Creates and returns a {@link OrderEntity}
     */
    public static OrderEntity getInvalidOrderEntity_NoCustomerName(){

        var orderItem1 = getValidOrderItemEntity(1,1);
        var orderItem2 = getValidOrderItemEntity(2,1);
        var orderItem3 = getValidOrderItemEntity(3,1);

        return OrderEntity
                .builder()
                .customerName("")
                .mobileNr("1234567890")
                .orderItemList(List.of(orderItem1,orderItem2,orderItem3))
                .build();
    }

    /**
     * Get invalid order entity with customer name not set.
     *
     * @return Creates and returns a {@link OrderEntity}
     */
    public static OrderEntity getInvalidOrderEntity_NoMobileNr(){

        var orderItem1 = getValidOrderItemEntity(1,1);
        var orderItem2 = getValidOrderItemEntity(2,1);
        var orderItem3 = getValidOrderItemEntity(3,1);

        return OrderEntity
                .builder()
                .customerName("mockCustomer")
                .mobileNr("")
                .orderItemList(List.of(orderItem1,orderItem2,orderItem3))
                .build();
    }

    /**
     * Get valid order entity.
     *
     * @return Creates and returns a {@link OrderEntity}
     */
    public static OrderEntity getInvalidOrderEntity_MobileNrNotSet(){

        var orderItem1 = getValidOrderItemEntity(1,1);
        var orderItem2 = getValidOrderItemEntity(2,1);
        var orderItem3 = getValidOrderItemEntity(3,1);

        return OrderEntity
                .builder()
                .customerName("mockCustomer")
                .mobileNr("")
                .orderItemList(List.of(orderItem1,orderItem2,orderItem3))
                .build();
    }

    /**
     * Get valid cart item entity.
     *
     * @return Creates and returns a {@link OrderItemEntity}
     */
    public static OrderItemEntity getValidOrderItemEntity(int productId, int quantity){

        return OrderItemEntity
                .builder()
                .productId(productId)
                .quantity(quantity)
                .build();
    }
}
