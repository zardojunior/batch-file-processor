package br.com.fileprocessor.exception;

public class ParseException extends RuntimeException {

    private static final long serialVersionUID = -162015653558943623L;

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }
}
