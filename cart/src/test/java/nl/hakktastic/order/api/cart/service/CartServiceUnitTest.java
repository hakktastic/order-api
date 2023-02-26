package nl.hakktastic.order.api.cart.service;

import nl.hakktastic.order.api.cart.exception.CartNotFoundException;
import nl.hakktastic.order.api.cart.exception.ProductAlreadyInCartException;
import nl.hakktastic.order.api.cart.repository.CartRepository;
import nl.hakktastic.order.api.cart.testdata.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceUnitTest {

    @InjectMocks
    CartService service;

    @Mock
    CartRepository repository;

    @Test
    void givenValidCartId_whenReadCart_thenReturnCartEntity(){

        var validCartEntity = TestDataGenerator.getValidCartEntity();

        when(repository.findById(anyInt())).thenReturn(Optional.of(validCartEntity));

        var result = service.readCart(validCartEntity.getId());

        assertThat(result)
                .isNotNull()
                .isPresent()
                .contains(validCartEntity);
    }

    @Test
    void givenCartDoesNotExist_whenReadCart_thenReturnEmptyOptional(){

        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        var result = service.readCart(any(Integer.class));

        assertThat(result)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void givenValidCartData_whenCreateCart_thenReturnCartEntity(){

        var validCartEntity = TestDataGenerator.getValidCartEntity();

        when(repository.save(any())).thenReturn(validCartEntity);

        var result = service.createCart(validCartEntity);

        assertThat(result)
                .isNotNull()
                .isPresent()
                .contains(validCartEntity);
    }

    @Test
    void givenProductExistsInCart_whenCreateCartItem_thenThrowProductAlreadyInCartException(){

        var validCartEntity = TestDataGenerator.getValidCartEntity();

        when(repository.findById(1)).thenReturn(Optional.of(validCartEntity));

        assertThatExceptionOfType(ProductAlreadyInCartException.class)
                .isThrownBy(() ->  service.createCartItem(1, 1, 25));

    }

    @Test
    void givenCartNotFound_whenCreateCartItem_thenThrowCartNotFoundException(){

        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThatExceptionOfType(CartNotFoundException.class)
                .isThrownBy(() ->  service.createCartItem(1, 1, 25));
    }
}
