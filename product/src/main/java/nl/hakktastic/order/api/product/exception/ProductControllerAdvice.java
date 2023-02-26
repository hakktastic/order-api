package nl.hakktastic.order.api.product.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

/**
 * Exception handler for service.
 *
 */
@Slf4j
@RestControllerAdvice
public class ProductControllerAdvice {

    @ApiResponse(responseCode = "404", description = "Product not found")
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ProductError handleProductNotFoundException(ProductNotFoundException exception, WebRequest request){

        return ProductError.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ApiResponse(responseCode = "400", description = "bad request")
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ProductError handleBadRequest(Exception exception, WebRequest request){

        return ProductError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }


    @ApiResponse(responseCode = "500", description = "unexpected error")
    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ProductError fallbackErrorHandler(Exception exception, WebRequest request){

        return ProductError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }
}