package nl.hakktastic.order.api.report.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.report.dto.OrderItemDTO;
import nl.hakktastic.order.api.report.dto.ProductDTO;
import nl.hakktastic.order.api.report.exception.OrderNotFoundException;
import nl.hakktastic.order.api.report.exception.ProductNotFoundException;
import nl.hakktastic.order.api.report.openfeign.OrderServiceFeignClient;
import nl.hakktastic.order.api.report.openfeign.ProductServiceFeignClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service object for Reports.
 *
 */
@Slf4j
@Service
public class ReportService {

    private final OrderServiceFeignClient orderServiceFeignClient;
    private final ProductServiceFeignClient productServiceFeignClient;

    /**
     * Constructor injection.
     *
     * @param orderServiceFeignClient feign client to connect to order service
     * @param productServiceFeignClient feign client to connect to product service
     */
    public ReportService(OrderServiceFeignClient orderServiceFeignClient, ProductServiceFeignClient productServiceFeignClient){

        this.orderServiceFeignClient = orderServiceFeignClient;
        this.productServiceFeignClient = productServiceFeignClient;
    }

    /**
     * Get daily sales amount.
     *
     * @return daily sales amount as {@link BigDecimal}
     */
    public Optional<BigDecimal> getDailySalesAmount(LocalDate startDate, LocalDate endDate){

        var optionalOrderList = orderServiceFeignClient.getAllOrders();

        if(optionalOrderList.isEmpty()){

            log.info("getDailySalesAmount - unable to retrieve orders from order-service");
            throw new OrderNotFoundException("unable to retrieve orders from order-service");
        }

        var orderList = optionalOrderList.get();
        log.debug("getDailySalesAmount - retrieved #{} orders", orderList.size());

        var startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0, 0));
        var endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));

        var dailySalesAmount = orderList
                .stream()
                //.peek(orderDTO -> log.info("order={} orderDateTime={} startDateTime={} endDateTime={} eligible={}", orderDTO.getId(), orderDTO.getOrderDateTime(), startDateTime, endDateTime, orderDTO.getOrderDateTime().isAfter(startDateTime) && orderDTO.getOrderDateTime().isBefore(endDateTime)))
                .filter(orderDTO -> orderDTO.getOrderDateTime().isAfter(startDateTime) && orderDTO.getOrderDateTime().isBefore(endDateTime))
                .flatMap(orderDTO -> orderDTO.getOrderItemList().stream())
                .map(orderItemDTO -> {

                    var optionalProductDTO = productServiceFeignClient.getProductById(orderItemDTO.getProductId());

                    if(optionalProductDTO.isEmpty()){
                        log.info("getDailySalesAmount - unable to retrieve product={} from order-service", orderItemDTO.getProductId());
                        throw new ProductNotFoundException("unable to retrieve product="+orderItemDTO.getProductId()+" from product-service");
                    }

                    var productPrice = optionalProductDTO.get().getPrice();
                    return productPrice.multiply(BigDecimal.valueOf(orderItemDTO.getQuantity()));

                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.debug("getDailySalesAmount - amount={}", dailySalesAmount.toString());

        return Optional.of(dailySalesAmount);
    }

    /**
     * Get top 5 sellings products of the day.
     *
     * @return {@link List} with product ID's
     */
    public Optional<List<ProductDTO>> getTopFiveSellingProductsOfTheDay(){

        var optionalOrderList = orderServiceFeignClient.getAllOrders();

        if(optionalOrderList.isEmpty()){

            log.info("getTopFiveSellingProductsOfTheDay - unable to retrieve orders from order-service");
            throw new OrderNotFoundException("unable to retrieve orders from order-service");
        }

        var orderList = optionalOrderList.get();
        log.debug("getDailySalesAmount - retrieved #{} orders", orderList.size());

        var today = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));

        var topFiveProductIdList = orderList
                .stream()
                //.peek(orderDTO -> log.debug("order={} is eligible for reporting today={}", orderDTO.getId(), orderDTO.getOrderDateTime().isAfter(today)))
                .filter(orderDTO -> orderDTO.getOrderDateTime().isAfter(today))
                .flatMap(orderDTO -> orderDTO.getOrderItemList().stream())
                .collect(Collectors.groupingBy(OrderItemDTO::getProductId, Collectors.counting()))
                .keySet()
                    .stream()
                    .sorted((p1,p2) -> Integer.compare(p2, p1))
                    .limit(5)
                .toList();

        var topFiveSellingProductOfTheDay = new ArrayList<ProductDTO>();

        log.info("top 5 products of the day:");
        topFiveProductIdList.forEach(productId -> {

            log.info("productId={}",productId);
            var optionalProductDTO = productServiceFeignClient.getProductById(productId);

            if(optionalProductDTO.isPresent()){

                var productDTO = optionalProductDTO.get();
                log.debug("productDTO of productId={} productDTO={}",productId, productDTO);
                topFiveSellingProductOfTheDay.add(productDTO);
            }
        });

        return Optional.of(topFiveSellingProductOfTheDay);
    }

    public Optional<ProductDTO> getLeastSellingProductOfTheMonth() {

        // TODO implementation
        return Optional.empty();
    }
}
