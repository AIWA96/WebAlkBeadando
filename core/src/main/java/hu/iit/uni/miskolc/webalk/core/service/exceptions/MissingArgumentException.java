package hu.iit.uni.miskolc.webalk.core.service.exceptions;

public class MissingArgumentException extends Exception {
    public MissingArgumentException() {
    }

    public MissingArgumentException(String s) {
        super(s);
    }

    public MissingArgumentException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MissingArgumentException(Throwable throwable) {
        super(throwable);
    }

    public MissingArgumentException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
