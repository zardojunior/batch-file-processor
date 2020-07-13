package br.com.fileprocessor.report;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fileprocessor.model.Customer;
import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.service.DataModel;

public class Reports {

	/**
	 * Returns the number of Customers.
	 * <p>NOTE: the DataModel values is an implementation of Set that prevents duplicate elements and
	 * the Customer model implements equals using CNPJ so it's just a matter of returning the Set size.
	 *
	 * @param data the {@link DataModel} instance containing all models read
	 * @return the number of distinct customers
	 */
	public static final Integer numberOfCustomers(DataModel data) {
		return data.getModelsOfType(Customer.TYPE).size();
	}

	/**
	 * Returns the number of Salesmen.
	 * <p>NOTE: the DataModel values is an implementation of Set that prevents duplicate elements and
	 * the Salesmen model implements equals using CPF so it's just a matter of returning the Set size.
	 *
	 * @param data the {@link DataModel} instance containing all models read
	 * @return the number of distinct salesmen
	 */
	public static final Integer numberOfSalesmens(DataModel data) {
		return data.getModelsOfType(Customer.TYPE).size();
	}

	/**
	 * Returns the salesman name with the lowest total sales amount.
	 *
	 * @param data the {@link DataModel} instance containing all models read
	 * @return the salesman name with the lowest total sales amount
	 */
	public static final String worstSalesman(DataModel data) {

		// Can we have salesmen without sale?

		// Grouping sales per salesman name
		Map<String, List<Sale>> salesGroupBySalesmanName = data.getModelsOfType(Sale.TYPE)
				.stream()
				.map(s -> (Sale)s)
				.collect(Collectors.groupingBy(Sale::getSalesmanName));

		// Calculating sales total per salesman name
		Map<String, BigDecimal> totalPerSalesmanName = new LinkedHashMap<>();
		for (Entry<String, List<Sale>> entry : salesGroupBySalesmanName.entrySet()) {
			totalPerSalesmanName.put(entry.getKey(), totalSalesPrice(entry.getValue()));
		}

		// Sorting per value (ascending)
		Optional<Map.Entry<String,BigDecimal>> sortedTotals =	totalPerSalesmanName.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).findFirst();

		if (sortedTotals.isPresent()) {
			return sortedTotals.get().getKey();
		}
		return null;
	}

	/**
	 * Returns the sum of all sales in the collection.
	 *
	 * @param sales the sales whose total prices will be summed
	 * @return the sum of sales total prices
	 */
	public static final BigDecimal totalSalesPrice(List<Sale> sales) {
		return sales.stream()
				.map(Sale::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * Returns the most expensive sale within the DataModel.
	 *
	 * @param data the {@link DataModel} instance containing all models read
	 * @return the most expensive sale
	 */
	public static final Sale mostExpensiveSale(DataModel data) {
		return data.getModelsOfType(Sale.TYPE)
				.stream()
				.map(m -> (Sale)m)
				.max(Comparator.comparing(Sale::getTotalPrice))
				.get();
	}

}
