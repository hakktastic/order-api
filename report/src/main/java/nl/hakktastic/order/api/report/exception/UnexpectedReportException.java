package nl.hakktastic.order.api.report.exception;

/**
 * Custom error when an unexpected error occurs.
 *
 */
public class UnexpectedReportException extends RuntimeException {

    public UnexpectedReportException(String message){
        super(message);
    }
}
