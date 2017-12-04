package hu.iit.uni.miskolc.webalk.core.service.exceptions;

public class PersistenceException extends Exception{
    public PersistenceException() {
    }

    public PersistenceException(String s) {
        super(s);
    }

    public PersistenceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PersistenceException(Throwable throwable) {
        super(throwable);
    }

    public PersistenceException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
