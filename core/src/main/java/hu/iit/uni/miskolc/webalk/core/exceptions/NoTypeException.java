package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoTypeException extends Throwable {
    public NoTypeException() {
    }

    public NoTypeException(String s) {
    }

    public NoTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTypeException(Throwable cause) {
        super(cause);
    }

    public NoTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
