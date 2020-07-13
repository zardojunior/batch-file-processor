package br.com.fileprocessor.service;

public interface Processor<I> {
	void process(I input);
}
