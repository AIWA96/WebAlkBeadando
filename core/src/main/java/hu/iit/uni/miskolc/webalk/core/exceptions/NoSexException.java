package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoSexException extends Exception {
    public NoSexException() {
    }

    public NoSexException(String message) {
        super(message);
    }

    public NoSexException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSexException(Throwable cause) {
        super(cause);
    }

    public NoSexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
