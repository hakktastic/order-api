package nl.hakktastic.order.api.order.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.order.entity.OrderEntity;
import nl.hakktastic.order.api.order.exception.OrderNotFoundException;
import nl.hakktastic.order.api.order.exception.UnexpectedOrderException;
import nl.hakktastic.order.api.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller for orders.
 *
 */
@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class OrderController {

    private final OrderService service;

    /**
     * Constructor injection.
     *
     * @param orderService service object for orders
     */
    public OrderController(OrderService orderService){

        this.service = orderService;
    }

    /**
     * Get order by ID.
     *
     * @param orderId ID of the order.
     * @return Returns @{@link OrderEntity}
     */
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderEntity> readOrder(@Valid @PathVariable int orderId){

        return new ResponseEntity<>(service.readOrder(orderId)
                .orElseThrow(() -> new OrderNotFoundException("no order found for id="+orderId)),
                HttpStatus.OK);
    }

    /**
     * Get all orders.
     *
     * @return Returns a {@link List} with {@link OrderEntity} objects
     */
    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> readAllOrders(){

        return new ResponseEntity<>(service.readAllOrders()
                .orElseThrow(() -> new OrderNotFoundException("no order(s) found")),
                HttpStatus.OK);
    }

    /**
     * Create order.
     *
     * @param orderEntity order metadata
     * @return placed order
     */
    @PostMapping("/orders")
    public ResponseEntity<OrderEntity> createOrder(@Valid @RequestBody OrderEntity orderEntity){

        return new ResponseEntity<>(service.createOrder(orderEntity)
                .orElseThrow(() -> new UnexpectedOrderException("unexpected error occurred while creating order="+orderEntity)),
                HttpStatus.CREATED);
    }
}
