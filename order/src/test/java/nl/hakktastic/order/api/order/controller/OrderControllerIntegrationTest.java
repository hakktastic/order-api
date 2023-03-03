package nl.hakktastic.order.api.order.controller;

import com.google.gson.Gson;
import nl.hakktastic.order.api.order.testdata.CustomGsonBuilder;
import nl.hakktastic.order.api.order.testdata.TestDataGenerator;
import org.junit.jupiter.api.BeforeAll;
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
class OrderControllerIntegrationTest {

    private static final String URL_TEMPLATE_ORDERS = "/api";

    private static Gson gson;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    static void beforeAll(){

        gson = CustomGsonBuilder.getGsonInstance();
    }

    @Test
    void givenValidOrderData_whenCreateOrder_thenReturnPlacedOrder() throws Exception {

        var validOrderEntity = TestDataGenerator.getValidOrderEntity();
        var validOrderItem1 = validOrderEntity.getOrderItemList().get(0);
        var validOrderItem2 = validOrderEntity.getOrderItemList().get(1);
        var validOrderItem3 = validOrderEntity.getOrderItemList().get(2);
        var jsonStrValidCartEntity = gson.toJson(validOrderEntity);

        mockMvc.perform(post(URL_TEMPLATE_ORDERS+"/orders")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonStrValidCartEntity))
                .andExpect(status().isCreated())
                // order
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customerName").value(validOrderEntity.getCustomerName()))
                .andExpect(jsonPath("$.mobileNr").value(validOrderEntity.getMobileNr()))
                .andExpect(jsonPath("$.orderDateTime").value(validOrderEntity.getOrderDateTime().toString()))
                // item 1
                .andExpect(jsonPath("$.orderItemList[0].id").value(1))
                .andExpect(jsonPath("$.orderItemList[0].productId").value(validOrderItem1.getProductId()))
                .andExpect(jsonPath("$.orderItemList[0].quantity").value(validOrderItem1.getQuantity()))
                // item 2
                .andExpect(jsonPath("$.orderItemList[1].id").value(2))
                .andExpect(jsonPath("$.orderItemList[1].productId").value(validOrderItem2.getProductId()))
                .andExpect(jsonPath("$.orderItemList[1].quantity").value(validOrderItem2.getQuantity()))
                // item 3
                .andExpect(jsonPath("$.orderItemList[2].id").value(3))
                .andExpect(jsonPath("$.orderItemList[2].productId").value(validOrderItem3.getProductId()))
                .andExpect(jsonPath("$.orderItemList[2].quantity").value(validOrderItem3.getQuantity()));
    }

    @Test
    void givenNoCustomerName_whenCreateOrder_thenReturnBadRequest() throws Exception {

        var invalidOrderEntity = TestDataGenerator.getInvalidOrderEntity_NoCustomerName();
        var jsonStrInvalidOrderEntity = gson.toJson(invalidOrderEntity);

        mockMvc.perform(post(URL_TEMPLATE_ORDERS+"/orders")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonStrInvalidOrderEntity))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenNoMobileNr_whenCreateOrder_thenReturnBadRequest() throws Exception {

        var invalidOrderEntity = TestDataGenerator.getInvalidOrderEntity_NoMobileNr();
        var jsonStrInvalidOrderEntity = gson.toJson(invalidOrderEntity);

        mockMvc.perform(post(URL_TEMPLATE_ORDERS+"/orders")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonStrInvalidOrderEntity))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidOrderId_whenReadOrder_thenReturnOrder() throws Exception {
        // TODO implementation
    }
}
