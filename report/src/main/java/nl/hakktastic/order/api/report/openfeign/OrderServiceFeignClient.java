package nl.hakktastic.order.api.report.openfeign;

import nl.hakktastic.order.api.report.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "order-service", url = "http://order-service:8082")
public interface OrderServiceFeignClient {

    @GetMapping("/api/orders/{orderId}")
    Optional<OrderDTO> getOrderById(@PathVariable int orderId);

    @GetMapping("/api/orders")
    Optional<List<OrderDTO>> getAllOrders();
}
