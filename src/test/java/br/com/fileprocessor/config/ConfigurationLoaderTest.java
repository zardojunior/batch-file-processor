package br.com.fileprocessor.config;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationLoaderTest {

    @Test
    public void testLoad() {
    	Configuration config = ConfigurationLoader.loadFromResource("config.yml", Configuration.class);
    	Assert.assertEquals("data/in", config.getInputDir());
    	Assert.assertEquals("data/out", config.getOutputDir());
    	Assert.assertEquals(".dat", config.getInputFileExtension());
    	Assert.assertEquals(".done.dat", config.getOutputFileExtension());
    	Assert.assertEquals(new Integer(20), config.getPoolingIntervalInSeconds());	
    }

}
