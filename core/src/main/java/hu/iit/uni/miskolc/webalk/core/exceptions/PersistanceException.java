package hu.iit.uni.miskolc.webalk.core.exceptions;

public class PersistanceException extends Exception{
    public PersistanceException() {
    }

    public PersistanceException(String s) {
        super(s);
    }

    public PersistanceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PersistanceException(Throwable throwable) {
        super(throwable);
    }

    public PersistanceException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
