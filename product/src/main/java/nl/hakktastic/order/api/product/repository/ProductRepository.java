package nl.hakktastic.order.api.product.repository;

import nl.hakktastic.order.api.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@link ProductEntity}.
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
