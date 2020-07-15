package br.com.fileprocessor.service;

import static br.com.fileprocessor.util.FileUtilities.isFileAccessible;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileProcessorTest {

    private final String INPUT_FILENAME = "test01.dat";
    private final String OUTPUT_FILENAME = "test01.done.dat";

    private Path inputFilenamePath;
    private Path outputFilenamePath;

    @Before
    public void before() throws IOException {
        this.inputFilenamePath = getFileFromResources(INPUT_FILENAME).toPath();
        this.outputFilenamePath = Paths.get(FileUtils.getTempDirectoryPath(), OUTPUT_FILENAME);
        Files.deleteIfExists(outputFilenamePath);
        Assert.assertTrue(isFileAccessible(inputFilenamePath.toFile()));
        Assert.assertFalse(outputFilenamePath.toFile().exists());
    }

    @Test
    public void testProcess() throws IOException {
        File file = inputFilenamePath.toFile();
        FileProcessor processor = new FileProcessor(FileUtils.getTempDirectoryPath(), ".done.dat");
        processor.process(file);
        Assert.assertTrue(isFileAccessible(outputFilenamePath.toFile()));
    }

    @After
    public void after() throws IOException {
        Files.deleteIfExists(outputFilenamePath);
    }

    private File getFileFromResources(String file) {
        return new File(getClass().getClassLoader().getResource(file).getFile());
    }

}
