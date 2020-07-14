package br.com.fileprocessor.converter;

public interface Converter<I, O> {
    O convert(I input);
}
