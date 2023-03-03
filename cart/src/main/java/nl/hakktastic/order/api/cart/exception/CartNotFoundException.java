package nl.hakktastic.order.api.cart.exception;

/**
 * Custom exception when no cart is found.
 *
 */
public class CartNotFoundException extends RuntimeException{

   public CartNotFoundException(String message){
       super(message);
   }
}
