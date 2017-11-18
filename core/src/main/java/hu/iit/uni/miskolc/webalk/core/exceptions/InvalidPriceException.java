package hu.iit.uni.miskolc.webalk.core.exceptions;

public class InvalidPriceException extends Exception {
    public InvalidPriceException() {
    }

    public InvalidPriceException(String s) {
        super(s);
    }

    public InvalidPriceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidPriceException(Throwable throwable) {
        super(throwable);
    }

    public InvalidPriceException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
