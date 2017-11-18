package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoTypeException extends Exception {
    public NoTypeException() {
    }

    public NoTypeException(String s) {
        super(s);
    }

    public NoTypeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NoTypeException(Throwable throwable) {
        super(throwable);
    }

    public NoTypeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
