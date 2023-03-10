package nl.hakktastic.order.api.cart.repository;

import nl.hakktastic.order.api.cart.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Cart Repository.
 */
@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
}
