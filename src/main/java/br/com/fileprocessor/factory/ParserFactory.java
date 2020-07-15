package br.com.fileprocessor.factory;

import java.util.HashMap;
import java.util.Map;

import br.com.fileprocessor.exception.ParserNotFoundException;
import br.com.fileprocessor.model.Customer;
import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.model.Salesman;
import br.com.fileprocessor.parser.CustomerParser;
import br.com.fileprocessor.parser.Parser;
import br.com.fileprocessor.parser.SaleParser;
import br.com.fileprocessor.parser.SalesmanParser;

public class ParserFactory {

    public static final String SALE_TYPE = Sale.TYPE;
    public static final String CUSTOMER_TYPE = Customer.TYPE;
    public static final String SALESMAN_TYPE = Salesman.TYPE;

    private static Map<String, Parser<String, String[]>> parsers = new HashMap<>();
    static {
        parsers.put(SALE_TYPE, new SaleParser());
        parsers.put(CUSTOMER_TYPE, new CustomerParser());
        parsers.put(SALESMAN_TYPE, new SalesmanParser());
    }

    public static Parser<String, String[]> create(String type) {
        return parsers.get(type);
    }

    public static Parser<String, String[]> createThrowsException(String type) {
        Parser<String, String[]> parser = create(type);
        if (parser == null) {
            String errorMessage = String.format("No parser found. Valid types are %s", parsers.keySet());
            throw new ParserNotFoundException(errorMessage);
        }
        return parser;
    }
}
