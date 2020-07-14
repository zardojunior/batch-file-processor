package br.com.fileprocessor.service;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

public class FileListener extends FileAlterationListenerAdaptor {

    private Processor<File> processor;

    public FileListener(Processor<File> processor) {
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
