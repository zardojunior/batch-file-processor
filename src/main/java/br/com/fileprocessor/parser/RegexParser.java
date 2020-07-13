package br.com.fileprocessor.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.fileprocessor.exception.ParseException;

public class RegexParser {

	private static final Logger log = LoggerFactory.getLogger(RegexParser.class);

	private Pattern pattern;

	public RegexParser(String regex) {
		this.pattern = Pattern.compile(regex);
	}

	public String[] parse(String string) {
		try {
			Matcher matcher = pattern.matcher(string);
			if (!matcher.matches()) {
				log.warn("The sequence '{}' does not match the regular expression '{}'", string, matcher.pattern());
				return new String[] {};
			}
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
