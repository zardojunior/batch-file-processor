package br.com.fileprocessor.parser;

import java.util.function.Function;

import br.com.fileprocessor.model.Sale;

public class SaleParser implements Function<String, String[]> {

	private static final char SEP = '�';

	private RegexParser regexParser;

	/**
	 * The sale row format is
	 * <pre>003�Sale ID�[Item ID-Item Quantity-Item Price]�Salesman name</pre>
	 * Regex must match the following example:
	 * <pre>003�10�[1-10-100,2-30-2.50,3-40-3.10]�Pedro</pre>
	 */
	public static final String DEFAULT_REGEX = "("+Sale.TYPE+")"+SEP+"(\\d*)"+SEP+"(.*)"+SEP+"(.*)";

	/**
	 * Create a SaleParser with the default REGEX.
	 */
	public SaleParser() {
		this.regexParser = new RegexParser(DEFAULT_REGEX);
	}

	/**
	 * Create a SaleParser with a custom defined REGEX.
	 *
	 * @param regex the custom defined regex to match.
	 */
	public SaleParser(String regex) {
		this.regexParser = new RegexParser(regex);
	}

	@Override
	public String[] apply(String string) {
		return regexParser.parse(string);
	}

}
