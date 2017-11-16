package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoNameException extends Exception {
    public NoNameException() {
    }

    public NoNameException(String message) {
        super(message);
    }

    public NoNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoNameException(Throwable cause) {
        super(cause);
    }

    public NoNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
