package br.com.fileprocessor.converter;

import java.util.Arrays;

import br.com.fileprocessor.exception.ConversionException;
import br.com.fileprocessor.model.Customer;
import br.com.fileprocessor.model.Model;

public class CustomerConverter implements Converter<String[], Model> {

	@Override
	public Customer convert(String[] data) {
		try {
			return Customer.builder()
					.withCnpj(data[1])
					.withName(data[2])
					.withBusinessArea(data[3])
					.build();
		} catch (Exception e) {
			String errorMessage = String.format("Could not convert the data %s", Arrays.asList(data));
			throw new ConversionException(errorMessage, e);
		}
	}

}
