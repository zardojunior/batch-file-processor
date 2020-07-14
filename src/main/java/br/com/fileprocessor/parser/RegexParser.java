package br.com.fileprocessor.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.fileprocessor.exception.ParseException;

public class RegexParser {

    private Pattern pattern;

    public RegexParser(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public String[] parse(String string) {
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches()) {
            String errorMessage = String.format("The string '%s' does not match the regular expression '%s'", string, matcher.pattern());
            throw new ParseException(errorMessage);
        }
        try {
            String[] groups = new String[matcher.groupCount()];
            for (int i = 0 ; i < matcher.groupCount(); i++) {
                groups[i] = matcher.group(i+1);
            }
            return groups;
        } catch (Exception e) {
            String errorMessage = String.format("Could not parse the string %s", string);
            throw new ParseException(errorMessage, e);
        }
    }

}
