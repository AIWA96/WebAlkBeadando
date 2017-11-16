package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoEmployeeException extends Exception {
    public NoEmployeeException() {
    }

    public NoEmployeeException(String message) {
        super(message);
    }

    public NoEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEmployeeException(Throwable cause) {
        super(cause);
    }

    public NoEmployeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
