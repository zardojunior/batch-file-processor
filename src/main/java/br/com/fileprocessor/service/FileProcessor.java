package br.com.fileprocessor.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileprocessor.converter.CustomerConverter;
import br.com.fileprocessor.converter.SaleConverter;
import br.com.fileprocessor.converter.SalesmanConverter;
import br.com.fileprocessor.model.Customer;
import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.model.Salesman;
import br.com.fileprocessor.parser.CustomerParser;
import br.com.fileprocessor.parser.SaleParser;
import br.com.fileprocessor.parser.SalesmanParser;
import br.com.fileprocessor.util.FileUtilities;

public class FileProcessor {

	private static final Logger log = LoggerFactory.getLogger(FileProcessor.class);

	private String outputDir;
	private String outputFileExtension;

	public FileProcessor(String outputDir, String outputFileExtension) {
		this.outputDir = outputDir;
		this.outputFileExtension = outputFileExtension;
		new File(outputDir).mkdirs();
	}

	public void process(File file) {

		try {
			List<String> lines = Files.readAllLines(file.toPath());

			Set<Salesman> salesmen = lines.stream()
					.filter(l -> StringUtils.startsWith(l,Salesman.TYPE))
					.map(new SalesmanParser())
					.filter(Objects::nonNull)
					.map(new SalesmanConverter())
					.collect(Collectors.toSet());

			Set<Customer> customers = lines.stream()
					.filter(l -> StringUtils.startsWith(l, Customer.TYPE))
					.map(new CustomerParser())
					.filter(Objects::nonNull)
					.map(new CustomerConverter())
					.collect(Collectors.toSet());

			Set<Sale> sales = lines.stream()
					.filter(l -> StringUtils.startsWith(l, Sale.TYPE))
					.map(new SaleParser())
					.filter(Objects::nonNull)
					.map(new SaleConverter())
					.collect(Collectors.toSet());

			System.out.println(customers);

			Sale mostExpensiveSale = sales.parallelStream()
					.max(Comparator.comparing(Sale::getTotalPrice))
					.get();

			String numberOfCustomers = String.format("Quantidade ççde clientes no arquivo de entrada: %s", customers.size());
			String numberOfSalesmen = String.format("Quantidade de vendedor no arquivo de entrada: %s", salesmen.size());
			String mostExpensiveSaleId = String.format("ID da venda mais cara: %s", mostExpensiveSale.getSaleId());
			String worstSalesmanName = String.format("Pior vendedor: %s", ""); // FIXME

			String outputFileName = FilenameUtils.getBaseName(file.getName()).concat(outputFileExtension);
			Path path = Paths.get(FileUtilities.getUserDirectoryBasedPath(outputDir).toString(), outputFileName);
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

}
