package hu.iit.uni.miskolc.webalk.service.dao.exceptions;

public class WrongFormatException extends Exception {
    public WrongFormatException() {
    }

    public WrongFormatException(String s) {
        super(s);
    }

    public WrongFormatException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongFormatException(Throwable throwable) {
        super(throwable);
    }

    public WrongFormatException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
