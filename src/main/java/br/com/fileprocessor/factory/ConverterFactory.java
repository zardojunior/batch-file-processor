package br.com.fileprocessor.factory;

import java.util.HashMap;
import java.util.Map;

import br.com.fileprocessor.converter.Converter;
import br.com.fileprocessor.converter.CustomerConverter;
import br.com.fileprocessor.converter.SaleConverter;
import br.com.fileprocessor.converter.SalesmanConverter;
import br.com.fileprocessor.exception.ConverterNotFoundException;
import br.com.fileprocessor.model.Customer;
import br.com.fileprocessor.model.Model;
import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.model.Salesman;

public class ConverterFactory {

    public static final String SALE_TYPE = Sale.TYPE;
    public static final String CUSTOMER_TYPE = Customer.TYPE;
    public static final String SALESMAN_TYPE = Salesman.TYPE;

    private static Map<String, Converter<String[], Model>> converters = new HashMap<>();
    static {
        converters.put(SALE_TYPE, new SaleConverter());
        converters.put(CUSTOMER_TYPE, new CustomerConverter());
        converters.put(SALESMAN_TYPE, new SalesmanConverter());
    }

    public static Converter<String[], Model> create(String type) {
        return converters.get(type);
    }

    public static Converter<String[], Model> createThrowsException(String type) {
        Converter<String[], Model> converter = create(type);
        if (converter == null) {
            String errorMessage = String.format("No converter found. Valid types are %s", converters.keySet());
            throw new ConverterNotFoundException(errorMessage);
        }
        return converter;
    }
}
