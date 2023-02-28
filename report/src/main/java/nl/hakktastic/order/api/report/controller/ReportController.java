package nl.hakktastic.order.api.report.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import nl.hakktastic.order.api.report.dto.ProductDTO;
import nl.hakktastic.order.api.report.exception.UnexpectedReportException;
import nl.hakktastic.order.api.report.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ReportController {

    private final ReportService service;

    /**
     * Constructor injection.
     *
     * @param reportService service object for reports
     */
    public ReportController(ReportService reportService){

        this.service = reportService;
    }

    /**
     * Get daily sales amount.
     *
     * @return daily sales amount
     */
    @GetMapping("/reports/daily/sales/start/{startDate}/end/{endDate}")
    public ResponseEntity<BigDecimal> getDailySalesAmount(@Valid @PathVariable LocalDate startDate, @Valid @PathVariable LocalDate endDate){

        return new ResponseEntity<>(service.getDailySalesAmount(startDate, endDate)
                .orElseThrow(() -> new UnexpectedReportException("unexpected error occurred while calculating daily sales amount")),
                HttpStatus.OK);
    }

    /**
     * Get the top 5 selling products of the day.
     *
     * @return Returns a List with top-5 {@link ProductDTO} of the day
     */
    @GetMapping("/reports/daily/sales/top-5-products")
    public ResponseEntity<List<ProductDTO>> getTopFiveSellingProductsOfTheDay(){

        return new ResponseEntity<>(service.getTopFiveSellingProductsOfTheDay()
                .orElseThrow(() -> new UnexpectedReportException("unexpected error occurred while calculating top 5 selling products of the day")),
                HttpStatus.OK);
    }

    /**
     * Get the least selling product of the month.
     *
     * @return a @{@link ProductDTO} of the least selling product of the month
     */
    @GetMapping("/reports/monthly/sales/least-selling-product")
    public ResponseEntity<ProductDTO> getLeastSellingProductOfTheMonth(){

        return new ResponseEntity<>(service.getLeastSellingProductOfTheMonth()
                .orElseThrow(() -> new UnexpectedReportException("unexpected error occurred while calculating the least selling product of the month")),
                HttpStatus.OK);
    }
}
