package br.com.fileprocessor.exception;

public class ConverterNotFoundException extends ProcessorException {

    private static final long serialVersionUID = -2473949660752320388L;

    public ConverterNotFoundException(String message) {
        super(message);
    }

    public ConverterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConverterNotFoundException(Throwable cause) {
        super(cause);
    }
}
