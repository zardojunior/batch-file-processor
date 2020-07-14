package br.com.fileprocessor.parser;

import br.com.fileprocessor.model.Salesman;

public class SalesmanParser implements Parser<String, String[]> {

    private static final char SEP = 'ç';

    private RegexParser regexParser;

    /**
     * The salesman row format is
     * <pre>001çCPFçNameçSalary</pre>
     * Regex must match the following example:
     * <pre>001ç3245678865434çPauloç40000.99</pre>
     * Note: officially the CPF length is 11, but in the sample data was 13
     */
    public static final String DEFAULT_REGEX = "("+Salesman.TYPE+")"+SEP+"(\\d{11,13})"+SEP+"(.*)"+SEP+"(.*)";

    /**
     * Create a SalesmanParser with the default REGEX.
     */
    public SalesmanParser() {
        this.regexParser = new RegexParser(DEFAULT_REGEX);
    }

    /**
     * Create a SalesmanParser with a custom defined REGEX.
     *
     * @param regex the custom defined regex to match.
     */
    public SalesmanParser(String regex) {
        this.regexParser = new RegexParser(regex);
    }

    @Override
    public String[] parse(String string) {
        return regexParser.parse(string);
    }

}
