package nl.hakktastic.order.api.product.exception;

/**
 * Custom exception message if product is not found.
 *
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message){
        super(message);
    }
}
