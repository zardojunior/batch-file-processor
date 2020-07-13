package br.com.fileprocessor.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileprocessor.converter.Converter;
import br.com.fileprocessor.factory.ConverterFactory;
import br.com.fileprocessor.factory.ParserFactory;
import br.com.fileprocessor.model.Model;
import br.com.fileprocessor.parser.Parser;
import br.com.fileprocessor.report.Reports;

public class FileProcessor implements Processor<File> {

	private static final Logger log = LoggerFactory.getLogger(FileProcessor.class);

	private String outputDir;
	private String outputFileExtension;

	public FileProcessor(String outputDir, String outputFileExtension) {
		this.outputDir = outputDir;
		this.outputFileExtension = outputFileExtension;
	}

	@Override
	public void process(File file) {

		try {
			DataModel dataModel = new DataModel(buildModelMap(file));

			String numberOfCustomers = String.format("Quantidade de clientes no arquivo de entrada: %s", Reports.numberOfCustomers(dataModel));
			String numberOfSalesmen = String.format("Quantidade de vendedor no arquivo de entrada: %s", Reports.numberOfSalesmens(dataModel));
			String mostExpensiveSaleId = String.format("ID da venda mais cara: %s", Reports.mostExpensiveSale(dataModel).getSaleId());
			String worstSalesmanName = String.format("Pior vendedor: %s", ""); // FIXME

			String outputFileName = FilenameUtils.getBaseName(file.getName()).concat(outputFileExtension);
			Path path = Paths.get(outputDir, outputFileName);
			FileUtils.writeLines(
					path.toFile(),
					StandardCharsets.UTF_8.toString(),
					Arrays.asList(numberOfCustomers, numberOfSalesmen, mostExpensiveSaleId, worstSalesmanName));

			log.info("File processed: {}", file.getName());

		} catch (Exception e) {
			String errorMessage = String.format("Could not process the file %s", file.getAbsolutePath());
			log.error(errorMessage, e);
		}
	}

	/**
	 * Build a map where the key is the entity type ID and the
	 * value is a collection of models with this type.
	 *
	 * @param file the file to read
	 * @return the map of types and lines of this type
	 * @throws IOException
	 */
	private Map<String, Set<Model>> buildModelMap(File file) throws IOException {
		Map<String, Set<Model>> modelMap = new HashMap<>();
		try (LineIterator iterator = FileUtils.lineIterator(file, StandardCharsets.UTF_8.toString())) {
			while (iterator.hasNext()) {
				String line = iterator.nextLine();
				if (line != null && line.length() > 3) {
					String type = StringUtils.substring(line, 0, 3);
					Parser<String, String[]> parser = ParserFactory.create(type);
					Converter<String[], Model> converter = ConverterFactory.create(type);
					Model model = converter.convert(parser.parse(line));
					addModelToMap(model, modelMap);
				} else {
					log.warn("Format of line is unknown. Ignoring! {}", line);
				}
			}
		}
		return modelMap;
	}

	/**
	 * Adds the {@link Model} instance into the map passed as reference.
	 *
	 * @param model the model to add into the map
	 * @param map the map where the model will be added
	 */
	private void addModelToMap(Model model, Map<String, Set<Model>> map) {
		if (map.containsKey(model.getType())) {
			map.get(model.getType()).add(model);
		} else {
			Set<Model> models = new HashSet<>();
			models.add(model);
			map.put(model.getType(), models);
		}
	}
}
