package nl.hakktastic.order.api.order.exception;

/**
 * Custom error when an unexpected error occurs.
 *
 */
public class UnexpectedOrderException extends RuntimeException {

    public UnexpectedOrderException(String message){
        super(message);
    }
}
