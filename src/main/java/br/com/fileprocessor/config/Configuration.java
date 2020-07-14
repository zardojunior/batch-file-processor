package br.com.fileprocessor.config;

public class Configuration {

    /**
     * The source directory for the files to be processed.
     */
    private String inputDir;

    /**
     * The target directory where the processed files will be stored.
     */
    private String outputDir;

    /**
     * The file extension of the source files.
     */
    private String inputFileExtension;

    /**
     * The file extension of the processed files.
     */
    private String outputFileExtension;

    /**
     * The amount of time in seconds to wait between checks of the input directory.
     */
    private Integer poolingIntervalInSeconds;

    public String getInputDir() {
        return inputDir;
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getInputFileExtension() {
        return inputFileExtension;
    }

    public void setInputFileExtension(String inputFileExtension) {
        this.inputFileExtension = inputFileExtension;
    }

    public String getOutputFileExtension() {
        return outputFileExtension;
    }

    public void setOutputFileExtension(String outputFileExtension) {
        this.outputFileExtension = outputFileExtension;
    }

    public Integer getPoolingIntervalInSeconds() {
        return poolingIntervalInSeconds;
    }

    public void setPoolingIntervalInSeconds(Integer poolingIntervalInSeconds) {
        this.poolingIntervalInSeconds = poolingIntervalInSeconds;
    }

    @Override
    public String toString() {
        return String.format(
                "Configuration [inputDir=%s, outputDir=%s, inputFileExtension=%s, outputFileExtension=%s, poolingIntervalInSeconds=%s]",
                inputDir, outputDir, inputFileExtension, outputFileExtension, poolingIntervalInSeconds);
    }

}
