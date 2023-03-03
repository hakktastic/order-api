package nl.hakktastic.order.api.cart.controller;

import com.google.gson.Gson;
import nl.hakktastic.order.api.cart.testdata.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CartControllerIntegrationTest {

    private static final String URL_TEMPLATE_CARTS = "/api";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenValidCartEntity_whenCreateCart_thenReturnOK() throws Exception {

        var validCartEntity = TestDataGenerator.getValidCartEntity();
        var validCartItem1 = validCartEntity.getCartItemList().get(0);
        var validCartItem2 = validCartEntity.getCartItemList().get(1);
        var validCartItem3 = validCartEntity.getCartItemList().get(2);
        var jsonStrValidCartEntity = new Gson().toJson(validCartEntity);

        mockMvc.perform(post(URL_TEMPLATE_CARTS+"/carts")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonStrValidCartEntity))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cartItemList[0].id").value(1))
                .andExpect(jsonPath("$.cartItemList[0].productId").value(validCartItem1.getProductId()))
                .andExpect(jsonPath("$.cartItemList[0].quantity").value(validCartItem1.getQuantity()))
                .andExpect(jsonPath("$.cartItemList[1].id").value(2))
                .andExpect(jsonPath("$.cartItemList[1].productId").value(validCartItem2.getProductId()))
                .andExpect(jsonPath("$.cartItemList[1].quantity").value(validCartItem2.getQuantity()))
                .andExpect(jsonPath("$.cartItemList[2].id").value(3))
                .andExpect(jsonPath("$.cartItemList[2].productId").value(validCartItem3.getProductId()))
                .andExpect(jsonPath("$.cartItemList[2].quantity").value(validCartItem3.getQuantity()));
    }

    @Test
    void givenNoProductID_whenCreateCart_thenReturnBadRequest(){
        // TODO implementation
    }

    @Test
    void givenNoQuantity_whenCreateCart_thenReturnBadRequest(){
        // TODO implementation
    }
}
