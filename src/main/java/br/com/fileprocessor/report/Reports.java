package br.com.fileprocessor.report;

import java.util.Comparator;

import br.com.fileprocessor.model.Customer;
import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.model.Salesman;
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


	public static final Salesman worstSalesman(DataModel data) {
		data.getModelsOfType(Salesman.TYPE);

		// FIXME
		return null;
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
