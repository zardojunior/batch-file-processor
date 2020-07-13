package br.com.fileprocessor.parser;

import java.util.function.Function;

import br.com.fileprocessor.model.Customer;

public class CustomerParser implements Function<String, String[]> {

	private static final char SEP = '�';

	private RegexParser regexParser;

	/**
	 * The customer row format is
	 * <pre>002�CNPJ�Name�Business Area</pre>
	 * Regex must match the following example:
	 * <pre>002�2345675433444345�Eduardo Pereira�Rural</pre>
	 */
	private static final String DEFAULT_REGEX = "("+Customer.TYPE+")"+SEP+"(\\d{14,16})"+SEP+"(.*)"+SEP+"(.*)";

	/**
	 * Create a CustomerParser with the default REGEX.
	 */
	public CustomerParser() {
		this.regexParser = new RegexParser(DEFAULT_REGEX);
	}

	/**
	 * Create a CustomerParser with a custom defined REGEX.
	 *
	 * @param regex the custom defined regex to match.
	 */
	public CustomerParser(String regex) {
		this.regexParser = new RegexParser(regex);
	}

	@Override
	public String[] apply(String string) {
		return regexParser.parse(string);
	}

}
