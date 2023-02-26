package nl.hakktastic.order.api.product.controller;

import nl.hakktastic.order.api.product.testdata.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerIntegrationTest {

    private static final String URL_TEMPLATE_PRODUCTS = "/api/products";

    @Autowired
    private MockMvc mockMvc;


    @Test
    void givenExistingProductId_whenReadProduct_thenReturnProduct() throws Exception {

        var validProductEntity = TestDataGenerator.getValidProductEntity();

        mockMvc.perform(get(URL_TEMPLATE_PRODUCTS+"/" + validProductEntity.getProductId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(validProductEntity.getProductId()))
                .andExpect(jsonPath("$.name").value(validProductEntity.getName()))
                .andExpect(jsonPath("$.price").value(validProductEntity.getPrice()));

    }

    @Test
    void givenNonExistingProductId_whenReadProduct_thenReturnProductNotFoundExecption() throws Exception {

        mockMvc.perform(get(URL_TEMPLATE_PRODUCTS+"/" + TestDataGenerator.PRODUCT_ID_INVALID)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }
}
