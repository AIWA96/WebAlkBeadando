package hu.iit.uni.miskolc.webalk.core.service.exceptions;

public class StorageProblemException extends Exception {
    public StorageProblemException() {
    }

    public StorageProblemException(String s) {
        super(s);
    }

    public StorageProblemException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public StorageProblemException(Throwable throwable) {
        super(throwable);
    }

    public StorageProblemException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
