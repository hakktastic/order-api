package nl.hakktastic.order.api.product.service;

import nl.hakktastic.order.api.product.repository.ProductRepository;
import nl.hakktastic.order.api.product.testdata.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUnitTest {

    @InjectMocks
    ProductService service;

    @Mock
    ProductRepository repository;

    @Test
    void givenExistingProductId_whenReadProduct_thenReturnProduct(){

        var validProductEntity = TestDataGenerator.getValidProductEntity();

        when(repository.findById(anyInt())).thenReturn(Optional.of(validProductEntity));

        var result = service.readProduct(validProductEntity.getProductId());

        assertThat(result)
                .isPresent()
                .contains(validProductEntity);
    }

    @Test
    void givenNonExistingProductId_whenReadProduct_thenReturnEmptyOptional(){

        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        var result = service.readProduct(anyInt());

        assertThat(result).isNotPresent();
    }
}
