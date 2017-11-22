package hu.iit.uni.miskolc.webalk.core.exceptions;

public class WrongDataTypeException extends Exception {
    public WrongDataTypeException() {
    }

    public WrongDataTypeException(String s) {
        super(s);
    }

    public WrongDataTypeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongDataTypeException(Throwable throwable) {
        super(throwable);
    }

    public WrongDataTypeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
