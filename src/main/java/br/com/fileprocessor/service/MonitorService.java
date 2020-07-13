package br.com.fileprocessor.service;

import static br.com.fileprocessor.util.FileUtilities.getUserDirectoryBasedPath;
import static br.com.fileprocessor.util.FileUtilities.listFilesFromUserDirectory;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileprocessor.config.Configuration;

public class MonitorService {

	private static final Logger log = LoggerFactory.getLogger(MonitorService.class);

	private Configuration configuration;

	public MonitorService(Configuration configuration) {
		this.configuration = configuration;
	}

	public void start() throws Exception {

		Path pathToObserve = getUserDirectoryBasedPath(configuration.getDataInputDir());
		FileFilter fileFilter = new SuffixFileFilter(configuration.getDataInputFileExtension());

		FileAlterationMonitor monitor = new FileAlterationMonitor(Duration.ofSeconds(configuration.getDirPoolingIntervalInSeconds()).toMillis());
		FileAlterationObserver observer = new FileAlterationObserver(pathToObserve.toString(), fileFilter);
		FileAlterationListener listener = new FileListener(new FileProcessor(configuration.getDataOutputDir(), configuration.getDataOutputFileExtension()));

		observer.addListener(listener);
		monitor.addObserver(observer);
		invokeListeners(observer.getListeners());
		monitor.start();

		log.info("File monitor started! {}", monitor.getObservers());
	}

	/**
	 * Get all files and eagealy triggers the listeners.
	 */
	private void invokeListeners(Iterable<FileAlterationListener> listeners) {
		File[] files = listFilesFromUserDirectory(
				configuration.getDataInputDir(),
				configuration.getDataInputFileExtension());
		Stream.of(files).forEach(file -> listeners.forEach(l -> l.onFileCreate(file)));
	}

}
