package hu.iit.uni.miskolc.webalk.service.dao.exceptions;

public class NoArgumentException extends Exception {
    public NoArgumentException() {
    }

    public NoArgumentException(String s) {
        super(s);
    }

    public NoArgumentException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NoArgumentException(Throwable throwable) {
        super(throwable);
    }

    public NoArgumentException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
