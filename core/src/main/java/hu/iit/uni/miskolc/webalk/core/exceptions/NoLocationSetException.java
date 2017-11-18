package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoLocationSetException extends Exception {
    public NoLocationSetException() {
    }

    public NoLocationSetException(String s) {
        super(s);
    }

    public NoLocationSetException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NoLocationSetException(Throwable throwable) {
        super(throwable);
    }

    public NoLocationSetException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
