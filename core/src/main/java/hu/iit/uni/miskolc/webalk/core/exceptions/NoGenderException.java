package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoGenderException extends Exception {
    public NoGenderException() {
    }

    public NoGenderException(String message) {
        super(message);
    }

    public NoGenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoGenderException(Throwable cause) {
        super(cause);
    }

    public NoGenderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
