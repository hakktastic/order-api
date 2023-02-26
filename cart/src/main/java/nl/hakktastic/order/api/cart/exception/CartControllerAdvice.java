package nl.hakktastic.order.api.cart.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

/**
 * Global exception handling for service.
 *
 */
@RestControllerAdvice
public class CartControllerAdvice {

    @ApiResponse(responseCode = "404", description = "Cart not found")
    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public CartError handleProductNotFoundException(CartNotFoundException exception, WebRequest request){

        return CartError.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ApiResponse(responseCode = "409", description = "product already exists in cart")
    @ExceptionHandler({ProductAlreadyInCartException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public CartError handleBadRequest(ProductAlreadyInCartException exception, WebRequest request){

        return CartError.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ApiResponse(responseCode = "400", description = "bad request")
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public CartError handleBadRequest(Exception exception, WebRequest request){

        return CartError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }


    @ApiResponse(responseCode = "500", description = "unexpected error")
    @ExceptionHandler({Exception.class, UnexpectedCartException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CartError fallbackErrorHandler(Exception exception, WebRequest request){

        return CartError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }
}
