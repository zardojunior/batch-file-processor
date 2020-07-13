package br.com.fileprocessor.util;

import static org.apache.commons.io.FileUtils.getUserDirectoryPath;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.filefilter.SuffixFileFilter;

public class FileUtilities {

	/**
	 * Returns all files from the supplied input directory, relative to the user directory.
	 *
	 * @param directory the directory path to be joined to the user directory path
	 * @param fileFilter the file filter
	 * @return the array of files found
	 */
	public static File[] listFilesFromUserDirectory(String directory, FileFilter fileFilter) {
		return getUserDirectoryBasedPath(directory).toFile().listFiles(fileFilter);
	}

	/**
	 * Returns all files from the supplied input directory relative to the user directory.
	 *
	 * @param directory the directory path to be joined to the user directory path
	 * @param fileExtension the file extension to filter
	 * @return the array of files found
	 */
	public static File[] listFilesFromUserDirectory(String directory, String fileExtension) {
		return listFilesFromUserDirectory(directory, new SuffixFileFilter(fileExtension));
	}

	/**
	 * Returns the resulting {@link Path} by joining the
	 * user directory path with the supplied directory path.
	 *
	 * @param directory the directory path to be joined to the user directory path
	 * @return the resulting {@link Path}
	 */
	public static Path getUserDirectoryBasedPath(String directory) {
		return Paths.get(getUserDirectoryPath(), directory);
	}

}
