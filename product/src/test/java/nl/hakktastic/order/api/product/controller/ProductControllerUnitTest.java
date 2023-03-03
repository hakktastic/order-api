package nl.hakktastic.order.api.product.controller;

import nl.hakktastic.order.api.product.exception.ProductNotFoundException;
import nl.hakktastic.order.api.product.service.ProductService;
import nl.hakktastic.order.api.product.testdata.TestDataGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerUnitTest {

    @InjectMocks
    ProductController controller;

    @Mock
    ProductService service;

    @Test
    void givenExistingProductId_whenReadProduct_thenReturnProduct(){

        var validProductEntity = TestDataGenerator.getValidProductEntity();

        when(service.readProduct(anyInt())).thenReturn(Optional.ofNullable(validProductEntity));

        var result = controller.readProduct(1);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody()).isEqualTo(validProductEntity);
    }

    @Test
    void givenNonExistingProductId_whenReadProduct_thenReturnProductNotFoundExecption(){

        when(service.readProduct(anyInt())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ProductNotFoundException.class)
                .isThrownBy(() -> controller.readProduct(TestDataGenerator.PRODUCT_ID_INVALID));
    }
}
