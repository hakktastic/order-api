package nl.hakktastic.order.api.report.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ReportControllerAdvice {

    @ApiResponse(responseCode = "400", description = "bad request")
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ReportError handleBadRequest(Exception exception, WebRequest request){

        return ReportError.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ApiResponse(responseCode = "404", description = "Order not found")
    @ExceptionHandler({ReportNotFoundException.class, OrderNotFoundException.class, ProductNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ReportError handleProductNotFoundException(RuntimeException exception, WebRequest request){

        return ReportError.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }


    @ApiResponse(responseCode = "500", description = "unexpected error")
    @ExceptionHandler({Exception.class, UnexpectedReportException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ReportError fallbackErrorHandler(Exception exception, WebRequest request){

        return ReportError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .description(request.getDescription(false))
                .build();
    }
}
