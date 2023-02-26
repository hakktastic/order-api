package nl.hakktastic.order.api.cart.exception;

/**
 * Custom exception when a product already exists in the cart.
 *
 */
public class ProductAlreadyInCartException extends RuntimeException {

    public ProductAlreadyInCartException(String message) {
        super(message);
    }
}
