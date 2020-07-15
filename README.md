# Batch File Processor


## How-to


1) The batch file processor application reads it's configuration from a YML file format. By default, if no customized file was informed the application will use it's default configuration file. If you need to use your custom configuration file, you need to create one like the following

```

# Input directory relative to the system user directory
inputDir: data/in

# Output directory relative to the system user directory
outputDir: data/out

# Source file extension
inputFileExtension: .dat

# Target file extension
outputFileExtension: .done.dat

# The amount of time in seconds to wait between checks of the input directory
poolingIntervalInSeconds: 20

# Continue to parse the file when a row is not recognized 
continueOnUnkownRow: true

```

2) Start the application and pass (or not) the configuration file created above as an argument

```
> java -jar batch-file-processor-xxx.jar config.yml
```
