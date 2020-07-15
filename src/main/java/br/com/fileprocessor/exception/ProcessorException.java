package br.com.fileprocessor.exception;

/**
 * Base exception for the processor.
 */
public class ProcessorException extends RuntimeException {

    private static final long serialVersionUID = 4476116088318198883L;

    public ProcessorException(String message) {
        super(message);
    }

    public ProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessorException(Throwable cause) {
        super(cause);
    }
}
