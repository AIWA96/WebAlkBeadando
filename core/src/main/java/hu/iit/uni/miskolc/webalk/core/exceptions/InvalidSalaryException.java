package hu.iit.uni.miskolc.webalk.core.exceptions;

public class InvalidSalaryException extends Exception {
    public InvalidSalaryException() {
    }

    public InvalidSalaryException(String message) {
        super(message);
    }

    public InvalidSalaryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSalaryException(Throwable cause) {
        super(cause);
    }

    public InvalidSalaryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
