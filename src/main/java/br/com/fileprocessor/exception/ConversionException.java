package br.com.fileprocessor.exception;

public class ConversionException extends ProcessorException {

    private static final long serialVersionUID = 1528581148540107575L;

    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionException(Throwable cause) {
        super(cause);
    }
}
