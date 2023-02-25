package nl.hakktastic.order.api.product.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Custom Error object to provide a consistent response.
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductError {

    private int statusCode;
    private LocalDateTime timestamp;
    private String description;
    private List<String> errorList;
    private String message;

}
