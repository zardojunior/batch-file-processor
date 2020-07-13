package br.com.fileprocessor.config;

public class Configuration {

	private String dataInputDir;
	private String dataOutputDir;
	private String dataInputFileExtension;
	private String dataOutputFileExtension;
	private Integer dirPoolingIntervalInSeconds;

	public String getDataInputDir() {
		return dataInputDir;
	}

	public void setDataInputDir(String dataInputDir) {
		this.dataInputDir = dataInputDir;
	}

	public String getDataOutputDir() {
		return dataOutputDir;
	}

	public void setDataOutputDir(String dataOutputDir) {
		this.dataOutputDir = dataOutputDir;
	}

	public String getDataInputFileExtension() {
		return dataInputFileExtension;
	}

	public void setDataInputFileExtension(String dataInputFileExtension) {
		this.dataInputFileExtension = dataInputFileExtension;
	}

	public String getDataOutputFileExtension() {
		return dataOutputFileExtension;
	}

	public void setDataOutputFileExtension(String dataOutputFileExtension) {
		this.dataOutputFileExtension = dataOutputFileExtension;
	}

	public Integer getDirPoolingIntervalInSeconds() {
		return dirPoolingIntervalInSeconds;
	}

	public void setDirPoolingIntervalInSeconds(Integer dirPoolingIntervalInSeconds) {
		this.dirPoolingIntervalInSeconds = dirPoolingIntervalInSeconds;
	}

	@Override
	public String toString() {
		return String.format(
				"Configuration [dataInputDir=%s,dataOutputDir=%s, dataInputFileExtension=%s, dataOutputFileExtension=%s, dirPoolingIntervalInSeconds=%s]",
				dataInputDir, dataOutputDir, dataInputFileExtension, dataOutputFileExtension,
				dirPoolingIntervalInSeconds);
	}

}
