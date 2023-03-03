package nl.hakktastic.order.api.cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.cart.entity.CartEntity;
import nl.hakktastic.order.api.cart.exception.CartNotFoundException;
import nl.hakktastic.order.api.cart.exception.UnexpectedCartException;
import nl.hakktastic.order.api.cart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


    @Operation(summary = "Create a cart")
    @ApiResponse(responseCode = "201", description = "Cart created successfully")
    @PostMapping("/carts")
    public ResponseEntity<CartEntity> createCart(@Valid @RequestBody CartEntity cartEntity){

        return new ResponseEntity<>(service.createCart(cartEntity)
                .orElseThrow(() -> new UnexpectedCartException("unexpected error occurred while creating cart")),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Update cart")
    @ApiResponse(responseCode = "200", description = "Cart updated successfully")
    @PutMapping("/carts/{cartId}/product/{productId}/quantity/{quantity}")
    public ResponseEntity<CartEntity> createCartItem(@Valid @PathVariable int cartId,  @Valid @PathVariable int productId, @Valid @PathVariable int quantity){

        return new ResponseEntity<>(service.createCartItem(cartId, productId, quantity)
                .orElseThrow(() -> new UnexpectedCartException("cart with id "+ cartId +"could not be updated")),
                HttpStatus.OK);
    }

    @Operation(summary = "Get cart with provided ID")
    @ApiResponse(responseCode = "200", description = "Cart returned successfully")
    @GetMapping("/carts/{cartId}")
    public ResponseEntity<CartEntity> readCart(@Valid @PathVariable int cartId){

        return new ResponseEntity<>(service.readCart(cartId)
                .orElseThrow(() -> new CartNotFoundException("No cart found for id="+cartId)),
                HttpStatus.OK);
    }
}
