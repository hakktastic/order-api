package nl.hakktastic.order.api.order.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.order.entity.OrderEntity;
import nl.hakktastic.order.api.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Order service.
 *
 */
@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;

    /**
     * Constructor injection.
     *
     * @param orderRepository repository for handling orders.
     */
    public OrderService(OrderRepository orderRepository){

        this.repository = orderRepository;
    }

    /**
     * Get order by id.
     *
     * @param orderId the id of the order
     * @return Returns an {@link OrderEntity} if found, otherwise returns empty {@link Optional}
     */
    public Optional<OrderEntity> readOrder(int orderId){

        log.debug("readOrder - order id={}", orderId);
        return repository.findById(orderId);
    }

    /**
     * Create order.
     *
     * @param orderEntity order to be created
     * @return Returns the created {@link OrderEntity}
     */
    public Optional<OrderEntity> createOrder(OrderEntity orderEntity){

        log.debug("createOrder - order={}", orderEntity);
        return Optional.of(repository.save(orderEntity));
    }
}
