package br.com.fileprocessor.util;

import static org.apache.commons.io.FileUtils.getUserDirectoryPath;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
     * @param file the file to write the lines into
     * @param lines the lines to write
     * @throws IOException
     */
    public static void writeLinesToFile(File file, String...lines) throws IOException {
        FileUtils.writeLines(
                file,
                StandardCharsets.UTF_8.toString(),
                Arrays.asList(lines));
    }

    /**
     * Check if the file exists, is a normal file (not a directory)
     * and if the application can read.
     *
     * @param file the file to check
     * @return <code>true</code> if the file exists,
     *         is a normal file and can be read
     */
    public static boolean isFileAccessible(File file) {
        return file != null
                && file.exists()
                && file.isFile()
                && file.canRead();
    }

}
