package nl.hakktastic.order.api.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.product.entity.ProductEntity;
import nl.hakktastic.order.api.product.exception.ProductNotFoundException;
import nl.hakktastic.order.api.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller to handle requests for products.
 *
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class ProductController {

    private final ProductService service;

    /**
     * Constructor injection.
     *
     * @param productService service object for products
     */
    public ProductController(ProductService productService){

        this.service = productService;
    }


    @Operation(summary = "read all products")
    @ApiResponse(responseCode = "200", description = "orders found in repository")
    @GetMapping(value = "/products", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductEntity>> readProducts() {

        log.debug("Product Controller - read all products");

        return new ResponseEntity<>(service.readProducts()
                .orElseThrow(() -> new ProductNotFoundException("No product(s) found")),
                HttpStatus.OK);
    }
}
