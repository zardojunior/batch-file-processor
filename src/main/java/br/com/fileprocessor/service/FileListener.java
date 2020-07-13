package br.com.fileprocessor.service;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

public class FileListener extends FileAlterationListenerAdaptor {

	private FileProcessor processor;

	public FileListener(FileProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void onFileChange(File file) {
		processor.process(file);
	}

	@Override
	public void onFileCreate(File file) {
		processor.process(file);
	}

}
