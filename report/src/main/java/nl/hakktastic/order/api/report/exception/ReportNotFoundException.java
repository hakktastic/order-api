package nl.hakktastic.order.api.report.exception;

/**
 * Custom exception when report is not found.
 *
 */
public class ReportNotFoundException extends RuntimeException {

    public ReportNotFoundException(String message){
        super(message);
    }
}
