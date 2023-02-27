package nl.hakktastic.order.api.order.exception;

/**
 * Custom exception when order is not found.
 *
 */
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }
}
