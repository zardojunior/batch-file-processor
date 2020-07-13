package br.com.fileprocessor;

import static br.com.fileprocessor.util.FileUtilities.getPathRelativeToUserDirectory;

import java.io.File;
import java.io.FileFilter;
import java.time.Duration;

import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import br.com.fileprocessor.config.Configuration;
import br.com.fileprocessor.config.ConfigurationLoader;
import br.com.fileprocessor.service.FileListener;
import br.com.fileprocessor.service.FileMonitorService;
import br.com.fileprocessor.service.FileProcessor;
import br.com.fileprocessor.service.Processor;
import br.com.fileprocessor.util.FileUtilities;

/**
 * Batch File Processor Application.
 */
public class FileProcessorApplication {

	public void run(Configuration configuration) throws Exception {

		String inputDir = getPathRelativeToUserDirectory(configuration.getInputDir());
		String outputDir = getPathRelativeToUserDirectory(configuration.getOutputDir());
		String outpurFileExtension = configuration.getOutputFileExtension();
		long poolingInterval = Duration.ofSeconds(configuration.getPoolingIntervalInSeconds()).toMillis();
		FileFilter fileFilter = new SuffixFileFilter(configuration.getInputFileExtension());

		FileUtilities.createDirectories(inputDir);
		FileUtilities.createDirectories(outputDir);

		Processor<File> fileProcessor = new FileProcessor(outputDir, outpurFileExtension);
		FileAlterationMonitor monitor = new FileAlterationMonitor(poolingInterval);
		FileAlterationObserver observer = new FileAlterationObserver(inputDir, fileFilter);
		FileAlterationListener listener = new FileListener(fileProcessor);

		observer.addListener(listener);
		monitor.addObserver(observer);

		new FileMonitorService(monitor).start();
	}

	public static void main(String[] args) throws Exception {
		Configuration configuration = ConfigurationLoader.loadConfiguration(args, Configuration.class);
		new FileProcessorApplication().run(configuration);
	}

}
