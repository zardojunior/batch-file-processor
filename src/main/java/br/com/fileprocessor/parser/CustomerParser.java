package br.com.fileprocessor.parser;

import br.com.fileprocessor.model.Customer;

public class CustomerParser implements Parser<String, String[]> {

	private static final char SEP = 'ç';

	private RegexParser regexParser;

	/**
	 * The customer row format is
	 * <pre>002çCNPJçNameçBusiness Area</pre>
	 * Regex must match the following example:
	 * <pre>002ç2345675433444345çEduardo PereiraçRural</pre>
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
	public String[] parse(String string) {
		return regexParser.parse(string);
	}

}
