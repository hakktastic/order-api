package nl.hakktastic.order.api.report.service;

import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.report.dto.OrderItemDTO;
import nl.hakktastic.order.api.report.dto.ProductDTO;
import nl.hakktastic.order.api.report.exception.OrderNotFoundException;
import nl.hakktastic.order.api.report.exception.ProductNotFoundException;
import nl.hakktastic.order.api.report.exception.ReportNotFoundException;
import nl.hakktastic.order.api.report.openfeign.OrderServiceFeignClient;
import nl.hakktastic.order.api.report.openfeign.ProductServiceFeignClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
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

        log.debug("getDailySalesAmount - started startDate={} endDate={}",startDate, endDate);

        var optionalOrderList = orderServiceFeignClient.getAllOrders();

        if(optionalOrderList.isEmpty()){

            log.info("getDailySalesAmount - no orders found");
            throw new OrderNotFoundException("getDailySalesAmount - no orders found");
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
                        throw new ProductNotFoundException("getDailySalesAmount - unable to retrieve product="+orderItemDTO.getProductId()+" from product-service");
                    }

                    var productPrice = optionalProductDTO.get().getPrice();
                    return productPrice.multiply(BigDecimal.valueOf(orderItemDTO.getQuantity()));

                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        log.debug("getDailySalesAmount - finished amount={}", dailySalesAmount.toString());

        return Optional.of(dailySalesAmount);
    }

    /**
     * Get top 5 sellings products of the day.
     *
     * @return {@link List} with product ID's
     */
    public Optional<List<ProductDTO>> getTopFiveSellingProductsOfTheDay(){

        log.debug("getTopFiveSellingProductsOfTheDay - started");

        var optionalOrderList = orderServiceFeignClient.getAllOrders();

        if(optionalOrderList.isEmpty()){

            log.info("getTopFiveSellingProductsOfTheDay - no orders found");
            throw new OrderNotFoundException("getTopFiveSellingProductsOfTheDay - no orders found");
        }

        var orderList = optionalOrderList.get();
        log.debug("getTopFiveSellingProductsOfTheDay - retrieved #{} orders", orderList.size());

        var today = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));

        var topFiveProductIdList = orderList
                .stream()
                //.peek(orderDTO -> log.debug("order={} is eligible for reporting today={}", orderDTO.getId(), orderDTO.getOrderDateTime().isAfter(today)))
                .filter(orderDTO -> orderDTO.getOrderDateTime().isAfter(today))
                .flatMap(orderDTO -> orderDTO.getOrderItemList().stream())
                .collect(Collectors.groupingBy(OrderItemDTO::getProductId, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((p1,p2) -> Long.compare(p2.getValue(), p1.getValue()))
                .limit(5)
                .toList();

        var topFiveSellingProductOfTheDay = new ArrayList<ProductDTO>();

        log.info("getTopFiveSellingProductsOfTheDay - top 5 products of the day:");
        topFiveProductIdList.forEach(product -> {

            log.info("getTopFiveSellingProductsOfTheDay - product={} quantity={}",product.getKey(), product.getValue());
            var optionalProductDTO = productServiceFeignClient.getProductById(product.getKey());

            if(optionalProductDTO.isPresent()){

                var productDTO = optionalProductDTO.get();
                log.debug("getTopFiveSellingProductsOfTheDay - productDTO={}", productDTO);
                topFiveSellingProductOfTheDay.add(productDTO);
            }
        });

        log.debug("getTopFiveSellingProductsOfTheDay - finished");

        return Optional.of(topFiveSellingProductOfTheDay);
    }

    /**
     * Get least selling product of the month.
     *
     * @param month month to be reported
     * @return a {@link ProductDTO} of least selling product of the month
     */
    public Optional<ProductDTO> getLeastSellingProductOfTheMonth(int month) {

        log.debug("getLeastSellingProductOfTheMonth - started month={}", month);

        var optionalOrderList = orderServiceFeignClient.getAllOrders();

        if(optionalOrderList.isEmpty()){

            log.info("getLeastSellingProductOfTheMonth - no orders found to report for month={}", month);
            throw new OrderNotFoundException("getLeastSellingProductOfTheMonth - no orders found to report for month=" + month);
        }

        var orderList = optionalOrderList.get();
        log.debug("getLeastSellingProductOfTheMonth - retrieved #{} orders", orderList.size());

        var leastSellingProductIdOfTheMonthList = orderList
                .stream()
                //.peek(orderDTO -> log.debug("order={} month={} is eligible={} for reporting", orderDTO.getId(), month, (orderDTO.getOrderDateTime().getYear() == Year.now().getValue()) && (orderDTO.getOrderDateTime().getMonth().getValue() == month)))
                .filter(orderDTO -> (orderDTO.getOrderDateTime().getYear() == Year.now().getValue()) && (orderDTO.getOrderDateTime().getMonth().getValue() == month))
                .flatMap(orderDTO -> orderDTO.getOrderItemList().stream())
                .collect(Collectors.groupingBy(OrderItemDTO::getProductId, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((p1,p2) -> Long.compare(p1.getValue(), p2.getValue()))
                .limit(1)
                .toList();

        log.debug("getLeastSellingProductOfTheMonth - least selling product of the month:");

        if(leastSellingProductIdOfTheMonthList.isEmpty()){
            log.info("getLeastSellingProductOfTheMonth - no reporting data found for provided month={}", month);
            throw new ReportNotFoundException("getLeastSellingProductOfTheMonth - no reporting data found for provided month=" + month);
        }

        var product = leastSellingProductIdOfTheMonthList.get(0);
        log.info("getLeastSellingProductOfTheMonth - product={} quantity={}", product.getKey(), product.getValue());

        log.debug("getLeastSellingProductOfTheMonth - finished");
        return productServiceFeignClient.getProductById(product.getKey());
    }
}
