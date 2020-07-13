package br.com.fileprocessor.util;

import static org.apache.commons.io.FileUtils.getUserDirectoryPath;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class FileUtilities {

	/**
	 * Returns all files from the input directory that matches the file extension.
	 *
	 * @param directory the directory path
	 * @param fileExtension the file extension to filter
	 * @return the array of files found
	 */
	public static Collection<File> listFiles(String directory, String fileExtension) {
		return FileUtils.listFiles(new File(directory), new String[]{fileExtension}, false);
	}

	/**
	 * Returns the resulting directory path by joining the
	 * user directory path with the supplied directory path.
	 *
	 * @param directory the directory path to be joined to the user directory path
	 * @return the resulting {@link Path}
	 */
	public static String getPathRelativeToUserDirectory(String directory) {
		return Paths.get(getUserDirectoryPath(), directory).toString();
	}

	/**
	 * Create missing directories.
	 *
	 * @param directory the directory to create
	 */
	public static void createDirectories(String directory) {
		new File(directory).mkdirs();
	}

	/**
	 * Write the lines into the given file.
	 *
	 * @param fileName the file to write the lines into
	 * @param lines the lines to write
	 */
	public static void writeLinesToFile(String fileName, String...lines) {

	}

}
