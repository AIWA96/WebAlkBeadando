package hu.iit.uni.miskolc.webalk.core.exceptions;

public class NoPostException extends Exception {
    public NoPostException() {
    }

    public NoPostException(String s) {
        super(s);
    }

    public NoPostException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NoPostException(Throwable throwable) {
        super(throwable);
    }

    public NoPostException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
