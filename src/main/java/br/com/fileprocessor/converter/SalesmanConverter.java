package br.com.fileprocessor.converter;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.fileprocessor.exception.ConversionException;
import br.com.fileprocessor.model.Model;
import br.com.fileprocessor.model.Salesman;

public class SalesmanConverter implements Converter<String[], Model> {

	@Override
	public Salesman convert(String[] data) {
		try {
			return Salesman.builder()
					.withCpf(data[1])
					.withName(data[2])
					.withSalary(new BigDecimal(data[3]))
					.build();
		} catch (Exception e) {
			String errorMessage = String.format("Could not convert the data %s", Arrays.asList(data));
			throw new ConversionException(errorMessage, e);
		}
	}

}
