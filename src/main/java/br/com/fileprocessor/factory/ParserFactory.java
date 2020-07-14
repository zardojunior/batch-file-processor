package br.com.fileprocessor.factory;

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

    public static Parser<String, String[]> create(String type) {
        switch (type) {
        case SALE_TYPE:
            return new SaleParser();
        case CUSTOMER_TYPE:
            return new CustomerParser();
        case SALESMAN_TYPE:
            return new SalesmanParser();
        default:
            return null;
        }
    }
}
