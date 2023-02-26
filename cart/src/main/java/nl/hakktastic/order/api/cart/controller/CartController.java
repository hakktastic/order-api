package nl.hakktastic.order.api.cart.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.cart.entity.CartEntity;
import nl.hakktastic.order.api.cart.exception.CartNotFoundException;
import nl.hakktastic.order.api.cart.exception.UnexpectedCartException;
import nl.hakktastic.order.api.cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CartController {

    private final CartService service;

    /**
     * Constructor injection.
     *
     * @param cartService service object for carts
     */
    public CartController(CartService cartService){

        this.service = cartService;
    }


    @PostMapping("/carts")
    public ResponseEntity<CartEntity> createCart(@RequestBody CartEntity cartEntity){

        return new ResponseEntity<>(service.createCart(cartEntity)
                .orElseThrow(() -> new UnexpectedCartException("unexpected error occurred while creating cart")),
                HttpStatus.CREATED);
    }

    @PutMapping("/carts/{cartId}/product/{productId}/quantity/{quantity}")
    public ResponseEntity<CartEntity> createCartItem(@PathVariable @Valid int cartId, @PathVariable @Valid int productId, @PathVariable @Valid int quantity){

        return new ResponseEntity<>(service.createCartItem(cartId, productId, quantity)
                .orElseThrow(() -> new UnexpectedCartException("cart with id "+ cartId +"could not be updated")),
                HttpStatus.OK);
    }

    @GetMapping("/carts/{cartId}")
    public ResponseEntity<CartEntity> readCart(@PathVariable @Valid int cartId){

        return new ResponseEntity<>(service.readCart(cartId)
                .orElseThrow(() -> new CartNotFoundException("No cart found for id="+cartId)),
                HttpStatus.OK);
    }
}
