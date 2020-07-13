package br.com.fileprocessor.converter;

import java.math.BigDecimal;
import java.util.function.Function;

import br.com.fileprocessor.model.Salesman;

public class SalesmanConverter implements Function<String[], Salesman> {

	@Override
	public Salesman apply(String[] data) {
		Salesman salesman = new Salesman();
		salesman.setCpf(data[1]);
		salesman.setName(data[2]);
		salesman.setSalary(new BigDecimal(data[3]));
		return salesman;
	}

}
