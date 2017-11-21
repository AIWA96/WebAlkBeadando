package hu.iit.uni.miskolc.webalk.core.exceptions;

public class InvalidGenderTypeException extends Exception {
    public InvalidGenderTypeException() {
    }

    public InvalidGenderTypeException(String message) {
        super(message);
    }

    public InvalidGenderTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidGenderTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidGenderTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
