package br.com.fileprocessor.report;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import br.com.fileprocessor.model.Customer;
import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.model.Salesman;
import br.com.fileprocessor.service.SalesData;

public class Reports {

	/**
	 * Returns the number of Customers.
	 * <p>NOTE: the SalesData values is an implementation of Set that prevents duplicate elements and
	 * the Customer model implements equals using CNPJ so it's just a matter of returning the Set size.
	 *
	 * @param data the {@link SalesData} instance containing all models read
	 * @return the number of distinct customers
	 */
	public static final Integer getNumberOfCustomers(SalesData data) {
		return data.getModelsOfType(Customer.TYPE).size();
	}

	/**
	 * Returns the number of Salesmen.
	 * <p>NOTE: the SalesData values is an implementation of Set that prevents duplicate elements and
	 * the Salesmen model implements equals using CPF so it's just a matter of returning the Set size.
	 *
	 * @param data the {@link SalesData} instance containing all models read
	 * @return the number of distinct salesmen
	 */
	public static final Integer getNumberOfSalesmen(SalesData data) {
		return data.getModelsOfType(Customer.TYPE).size();
	}

	/**
	 * Returns the salesman name with the lowest total sales amount.
	 *
	 * @param data the {@link SalesData} instance containing all models read
	 * @return the salesman name with the lowest total sales amount
	 */
	public static final Collection<String> getWorstSalesman(SalesData data) {

		// Can we have salesmen without sale?
		// If so, then return them without testing the rest of the sale data
		Collection<String> salesmanNamesWithoutSales = getSalesmanNamesWithoutSales(data);
		if (!salesmanNamesWithoutSales.isEmpty()) {
			return salesmanNamesWithoutSales;
		}

		// Grouping sales per salesman name
		Map<String, List<Sale>> salesGroupBySalesmanName = data.getModelsOfType(Sale.TYPE)
				.stream()
				.map(s -> (Sale)s)
				.collect(Collectors.groupingBy(Sale::getSalesmanName));

		// Calculating sales total per salesman name
		Map<String, BigDecimal> totalPerSalesmanName = new LinkedHashMap<>();
		for (Entry<String, List<Sale>> entry : salesGroupBySalesmanName.entrySet()) {
			totalPerSalesmanName.put(entry.getKey(), getTotalSalesPrice(entry.getValue()));
		}

		// Sorting per value (ascending)
		Optional<Map.Entry<String,BigDecimal>> sortedTotals =	totalPerSalesmanName.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).findFirst();

		if (sortedTotals.isPresent()) {
			return Arrays.asList(sortedTotals.get().getKey());
		}
		return Collections.emptyList();
	}

	/**
	 * Get salesman names without sales.
	 *
	 * @param the {@link SalesData} instance containing all models read
	 * @return  all salesman names from sales data
	 */
	public static final Collection<String> getSalesmanNamesWithoutSales(SalesData data) {
		Collection<String> allSalesmanNames = getAllSalesmanNames(data);
		Collection<String> allSalesmanNamesWithSales = getAllSalesmanNamesWithSales(data);
		return CollectionUtils.subtract(allSalesmanNames, allSalesmanNamesWithSales);
	}

	/**
	 * Get all salesman names from sales data.
	 *
	 * @param data the {@link SalesData} instance containing all models read
	 * @return all salesman names from sales data
	 */
	public static final Collection<String> getAllSalesmanNames(SalesData data) {
		return data.getModelsOfType(Salesman.TYPE)
				.stream()
				.map(s -> (Salesman)s)
				.map(Salesman::getName)
				.collect(Collectors.toSet());
	}

	/**
	 * Get all salesman names from sales data that have made at least one sale.
	 *
	 * @param data the {@link SalesData} instance containing all models read
	 * @return all salesman names from sales data that have made at least one sale
	 */
	public static final Collection<String> getAllSalesmanNamesWithSales(SalesData data) {
		return data.getModelsOfType(Sale.TYPE)
				.stream()
				.map(s -> (Sale)s)
				.map(Sale::getSalesmanName)
				.collect(Collectors.toSet());
	}

	/**
	 * Returns the sum of all sales in the collection.
	 *
	 * @param sales the sales whose total prices will be summed
	 * @return the sum of sales total prices
	 */
	private static final BigDecimal getTotalSalesPrice(List<Sale> sales) {
		return sales.stream()
				.map(Sale::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * Returns the most expensive sale within the SalesData.
	 *
	 * @param data the {@link SalesData} instance containing all models read
	 * @return the most expensive sale
	 */
	public static final Sale getMostExpensiveSale(SalesData data) {
		return data.getModelsOfType(Sale.TYPE)
				.stream()
				.map(m -> (Sale)m)
				.max(Comparator.comparing(Sale::getTotalPrice))
				.get();
	}

}
