package nl.hakktastic.order.api.order.repository;

import nl.hakktastic.order.api.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order Repository.
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
