package br.com.fileprocessor;

import br.com.fileprocessor.config.Configuration;
import br.com.fileprocessor.config.ConfigurationLoader;
import br.com.fileprocessor.service.MonitorService;

public class App {

	public static void main(String[] args) throws Exception {
		Configuration config = ConfigurationLoader.loadConfiguration(args, Configuration.class);
		new MonitorService(config).start();
	}

}