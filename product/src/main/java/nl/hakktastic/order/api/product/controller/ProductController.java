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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller to handle requests for products.
 *
 */
@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @ApiResponse(responseCode = "200", description = "product found in repository")
    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductEntity>> readProducts() {

        log.debug("Product Controller - read all products");

        return new ResponseEntity<>(service.readProducts()
                .orElseThrow(() -> new ProductNotFoundException("No product(s) found")),
                HttpStatus.OK);
    }


    @Operation(summary = "read product with provided ID")
    @ApiResponse(responseCode = "200", description = "product found in repository")
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductEntity> readProduct(@PathVariable @Valid int id) {

        log.debug("Product Controller - read product: productId={}", id);

        return new ResponseEntity<>(service.readProduct(id)
                .orElseThrow(() -> new ProductNotFoundException("No product found with id="+id)),
                HttpStatus.OK);
    }
}
