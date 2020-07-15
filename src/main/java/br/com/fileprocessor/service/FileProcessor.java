package br.com.fileprocessor.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import br.com.fileprocessor.exception.ProcessorException;
import br.com.fileprocessor.factory.ConverterFactory;
import br.com.fileprocessor.factory.ParserFactory;
import br.com.fileprocessor.model.Model;
import br.com.fileprocessor.parser.Parser;
import br.com.fileprocessor.report.Reports;
import br.com.fileprocessor.util.FileUtilities;

public class FileProcessor implements Processor<File> {

    private static final Logger log = LoggerFactory.getLogger(FileProcessor.class);
    private static final Integer TYPE_LENGTH = 3;

    private String outputDir;
    private String outputFileExtension;
    private boolean continueOnUnkownRow;

    private Map<String, Set<Model>> modelMap = new HashMap<>();

    public FileProcessor(boolean continueOnUnkownRow) {
        this.continueOnUnkownRow = continueOnUnkownRow;
    }

    public FileProcessor(String outputDir, String outputFileExtension, boolean continueOnUnkownRow) {
        this.outputDir = outputDir;
        this.outputFileExtension = outputFileExtension;
        this.continueOnUnkownRow = continueOnUnkownRow;
    }

    @Override
    public void process(File file) {

        try {
            log.info("Processing file {} ...", file.getAbsolutePath());
            SalesData salesData = buildSalesData(file);

            String numberOfCustomers = String.format("Quantidade de clientes no arquivo de entrada: %s", Reports.getNumberOfCustomers(salesData));
            String numberOfSalesmen = String.format("Quantidade de vendedor no arquivo de entrada: %s", Reports.getNumberOfSalesmen(salesData));
            String mostExpensiveSaleId = String.format("ID da venda mais cara: %s", Reports.getMostExpensiveSale(salesData).getSaleId());
            String worstSalesmanName = String.format("Pior vendedor: %s", Reports.getWorstSalesman(salesData));

            String outputFileName = FilenameUtils.getBaseName(file.getName()).concat(outputFileExtension);
            Path path = Paths.get(outputDir, outputFileName);
            FileUtilities.writeLinesToFile(path.toFile(), numberOfCustomers, numberOfSalesmen, mostExpensiveSaleId, worstSalesmanName);

            log.info("Report created: {}", path.toFile().getAbsolutePath());

        } catch (Exception e) {
            String errorMessage = String.format("Could not process the file %s", file.getAbsolutePath());
            log.error(errorMessage, e);
        }
    }

    /**
     * Build the sales data.
     *
     * @param file the file to read
     * @return the {@link SalesData} representing all models read from file
     * @throws IOException
     */
    public SalesData buildSalesData(File file) throws IOException {
        try (LineIterator iterator = FileUtils.lineIterator(file, StandardCharsets.UTF_8.toString())) {
            while (iterator.hasNext()) {
                String row = iterator.nextLine();
                if (StringUtils.isNotBlank(row)) {
                    processRow(row);
                }
            }
        }
        return new SalesData(modelMap);
    }

    /**
     * Process a row.
     *
     * @param row the row to be processed
     */
    private void processRow(String row) {
        try {
            String type = StringUtils.substring(row, 0, TYPE_LENGTH);
            Parser<String, String[]> parser = ParserFactory.createThrowsException(type);
            Converter<String[], Model> converter = ConverterFactory.createThrowsException(type);
            Model model = converter.convert(parser.parse(row));
            addModelToMap(model, modelMap);
        } catch (ProcessorException e) {
            if (!continueOnUnkownRow) {
                throw e;
            }
            String warnMessage = String.format("Skipping row %s: %s", row, e.getMessage());
            log.warn(warnMessage);
        }
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
