package br.com.fileprocessor.exception;

public class ParserNotFoundException extends ProcessorException {

    private static final long serialVersionUID = -357837396783825787L;

    public ParserNotFoundException(String message) {
        super(message);
    }

    public ParserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserNotFoundException(Throwable cause) {
        super(cause);
    }
}
