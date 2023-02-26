package nl.hakktastic.order.api.cart.exception;

/**
 * Custom exception when an unexpected error occurs.
 */
public class UnexpectedCartException extends RuntimeException {

    public UnexpectedCartException(String message){
        super(message);
    }
}
