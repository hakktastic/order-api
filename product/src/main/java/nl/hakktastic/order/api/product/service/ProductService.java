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

    /**
     * Get all products.
     *
     * @return Returns a {@link List} with {@link ProductEntity} objects
     */
    public Optional<List<ProductEntity>> readProducts(){

         return Optional.of(repository.findAll());
    }

    /**
     * Get product by product ID.
     *
     * @param productID the ID of a product
     * @return Return the {@link ProductEntity} if found, otherwise returns an empty {@link Optional}
     */
    public Optional<ProductEntity> readProduct(int productID){

        return repository.findById(productID);
    }
}