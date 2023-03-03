package nl.hakktastic.order.api.report.openfeign;

import nl.hakktastic.order.api.report.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", url = "http://product-service:8080")
public interface ProductServiceFeignClient {

    @GetMapping("/api/products/{id}")
    Optional<ProductDTO> getProductById(@PathVariable int id);

    @GetMapping("/api/products")
    Optional<List<ProductDTO>> getAllProducts();
}