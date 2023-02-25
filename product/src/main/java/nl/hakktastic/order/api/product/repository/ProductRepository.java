package nl.hakktastic.order.api.product.repository;

import nl.hakktastic.order.api.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for {@link ProductEntity}.
 *
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
