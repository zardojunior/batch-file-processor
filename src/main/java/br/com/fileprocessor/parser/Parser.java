package br.com.fileprocessor.parser;

public interface Parser<I, O> {
	O parse(I input);
}
