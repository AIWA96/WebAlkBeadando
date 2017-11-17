package hu.iit.uni.miskolc.webalk.core.exceptions;

public class InvalidPriceException extends Throwable {
    public InvalidPriceException() {
    }

    public InvalidPriceException(String s) {
    }

    public InvalidPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPriceException(Throwable cause) {
        super(cause);
    }

    public InvalidPriceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
