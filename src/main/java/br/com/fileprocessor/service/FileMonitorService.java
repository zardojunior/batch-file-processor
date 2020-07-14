package br.com.fileprocessor.service;

import java.io.File;
import java.io.FileFilter;
import java.util.stream.Stream;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File monitor service class.
 */
public class FileMonitorService {

    private static final Logger log = LoggerFactory.getLogger(FileMonitorService.class);

    private FileAlterationMonitor monitor;

    public FileMonitorService(FileAlterationMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * Starts the monitor service.
     *
     * @throws Exception
     */
    public void start() throws Exception {
        invokeFileCreationOnListeners();
        monitor.start();
        log.info("File monitor started! {}", monitor.getObservers());
    }

    /**
     * Get all files and eagealy triggers the file creation event on all listeners of all observers.
     *
     * @param files the collection of files to trigger the creation event
     */
    private void invokeFileCreationOnListeners() {
        for (FileAlterationObserver observer : monitor.getObservers()) {
            FileFilter fileFilter = observer.getFileFilter();
            File directory = observer.getDirectory();
            File[] files = directory.listFiles(fileFilter);
            for (FileAlterationListener listener : observer.getListeners()) {
                Stream.of(files).forEach(listener::onFileCreate);
            }
        }
    }

}
