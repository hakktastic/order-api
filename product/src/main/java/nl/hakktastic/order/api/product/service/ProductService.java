package nl.hakktastic.order.api.product.service;

import nl.hakktastic.order.api.product.entity.ProductEntity;
import nl.hakktastic.order.api.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling {@link nl.hakktastic.order.api.product.entity.ProductEntity}.
 */
@Service
public class ProductService {

    private final ProductRepository repository;

    /**
     * Construnctor injection.
     *
     * @param productRepository repository to persist products
     */
    public ProductService(ProductRepository productRepository){

        this.repository = productRepository;
    }

    public Optional<List<ProductEntity>> readProducts(){

         return Optional.of(repository.findAll());
    }
}