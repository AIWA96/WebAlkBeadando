package hu.iit.uni.miskolc.webalk.service.dao.exceptions;

public class CreateDataBaseException extends Exception{
    public CreateDataBaseException() {
    }

    public CreateDataBaseException(String s) {
        super(s);
    }

    public CreateDataBaseException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CreateDataBaseException(Throwable throwable) {
        super(throwable);
    }

    public CreateDataBaseException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
