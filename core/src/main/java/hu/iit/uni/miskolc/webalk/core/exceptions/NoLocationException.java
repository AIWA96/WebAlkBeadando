package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoLocationException extends Exception {
    public NoLocationException() {
    }

    public NoLocationException(String message) {
        super(message);
    }

    public NoLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLocationException(Throwable cause) {
        super(cause);
    }

    public NoLocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
