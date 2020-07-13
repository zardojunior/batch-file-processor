package br.com.fileprocessor.factory;

import br.com.fileprocessor.converter.Converter;
import br.com.fileprocessor.converter.CustomerConverter;
import br.com.fileprocessor.converter.SaleConverter;
import br.com.fileprocessor.converter.SalesmanConverter;
import br.com.fileprocessor.model.Customer;
import br.com.fileprocessor.model.Model;
import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.model.Salesman;

public class ConverterFactory {

	public static final String SALE_TYPE = Sale.TYPE;
	public static final String CUSTOMER_TYPE = Customer.TYPE;
	public static final String SALESMAN_TYPE = Salesman.TYPE;

	public static Converter<String[], Model> create(String type) {
		switch (type) {
		case SALE_TYPE:
			return new SaleConverter();
		case CUSTOMER_TYPE:
			return new CustomerConverter();
		case SALESMAN_TYPE:
			return new SalesmanConverter();
		default:
			return null;
		}
	}
}
