package br.com.fileprocessor.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class ConfigurationLoader {

	private static final String DEFAULT_CONFIG = "config.yml";

	private static final Logger log = LoggerFactory.getLogger(ConfigurationLoader.class);

	/**
	 * Load a configuration file from {@link InputStream}.
	 *
	 * @param inputStream the input stream to read from
	 * @param targetClass the target configuration class type
	 * @return the configuration instance
	 */
	public static <T> T load(InputStream inputStream, Class<?> targetClass) {
		Yaml yaml = new Yaml(new Constructor(targetClass));
		T configuration = yaml.load(inputStream);
		log.info("Configuration successfully loaded");
		return configuration;
	}

	/**
	 * Load a configuration for the given file.
	 *
	 * @param filename the file to read from
	 * @param targetClass the target configuration class type
	 * @return the configuration instance
	 */
	public static <T> T load(String filename, Class<?> targetClass) throws FileNotFoundException {
		return load(new FileInputStream(filename), targetClass);
	}

	/**
	 * Load a configuration for the given file in the application resources.
	 *
	 * @param filename the file to read from
	 * @param targetClass the target configuration class type
	 * @return the configuration instance
	 */
	public static <T> T loadFromResource(String filename, Class<?> targetClass) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(filename);
		return load(inputStream, targetClass);
	}

	/**
	 * Try to load a configuration file from the arguments otherwise loads the default configuration file.
	 *
	 * @param args the program arguments
	 * @param targetClass the target configuration class type
	 * @return the configuration instance
	 */
	public static Configuration loadConfiguration(String[] args, Class<?> targetClass) throws IOException {
		if (args != null && args.length > 0) {
			return load(args[0], targetClass);
		}
		log.warn("No configuration file provided");
		log.info("Loading default {} file", DEFAULT_CONFIG);
		return loadFromResource(DEFAULT_CONFIG, Configuration.class);
	}

}
