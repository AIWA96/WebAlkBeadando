package hu.iit.uni.miskolc.webalk.core.service.exceptions;

public class ExistingProblemException extends Exception {
    public ExistingProblemException() {
    }

    public ExistingProblemException(String s) {
        super(s);
    }

    public ExistingProblemException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ExistingProblemException(Throwable throwable) {
        super(throwable);
    }

    public ExistingProblemException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
