package nl.hakktastic.order.api.cart.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.cart.entity.CartEntity;
import nl.hakktastic.order.api.cart.entity.CartItemEntity;
import nl.hakktastic.order.api.cart.exception.CartNotFoundException;
import nl.hakktastic.order.api.cart.exception.ProductAlreadyInCartException;
import nl.hakktastic.order.api.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Cart Service.
 *
 */
@Slf4j
@Service
public class CartService {

    private final CartRepository repository;

    /**
     * Constructor injection.
     *
     * @param cartRepository repository containing carts
     */
    public CartService(CartRepository cartRepository){

        this.repository = cartRepository;
    }

    /**
     * Get cart with cart metadata and product(s).
     *
     * @param cartId the id of the cart to be returned
     * @return Returns a {@link CartEntity} containing metadata and product(s) in the cart
     */
    public Optional<CartEntity> readCart(int cartId){

        log.debug("readCart - cart id={}", cartId);
        return repository.findById(cartId);
    }

    public Optional<CartEntity> createCart(CartEntity cartEntity){

        log.debug("createCart - cart entity ={}", cartEntity);
        return Optional.of(repository.save(cartEntity));
    }

    public Optional<CartEntity> createCartItem(int cartId, int productId, int quantity){

        log.debug("createCartItem - cartId ={}, productId={}, quantity={}", cartId, productId, quantity);

        var optionalCart = repository.findById(cartId);

        if(optionalCart.isEmpty()){

            log.info("createCartItem - could not find cart with id={}", cartId);
            throw new CartNotFoundException("could not find cart with id={}" + cartId);
        }

        var optionalCartItemEntity = optionalCart
                .get().getCartItemList()
                .stream()
                .filter(e -> e.getProductId() == productId)
                .findFirst();

        if(optionalCartItemEntity.isPresent()){

            log.info("createCartItem - product with productId={} already exists", productId);
            throw new ProductAlreadyInCartException("product with productId="+productId+" already exists in cart="+cartId);
        }

        var item = CartItemEntity
                .builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        optionalCart
                .get()
                .getCartItemList()
                .add(item);

        repository.save(optionalCart.get());

        log.debug("createCartItem - cart item created cart={}",optionalCart.get());

        return optionalCart;
    }
}
