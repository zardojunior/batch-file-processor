package br.com.fileprocessor.converter;

import java.util.function.Function;

import br.com.fileprocessor.model.Customer;

public class CustomerConverter implements Function<String[], Customer> {

	@Override
	public Customer apply(String[] data) {
		Customer customer = new Customer();
		customer.setCnpj(data[1]);
		customer.setName(data[2]);
		customer.setBusinessArea(data[3]);
		return customer;
	}

}
