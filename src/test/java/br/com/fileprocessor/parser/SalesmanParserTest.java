package br.com.fileprocessor.parser;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.exception.ParseException;

public class SalesmanParserTest {

	@Test
	public void testParse() {
		String data = "001ç3245678865434çPauloç40000.99";
		String[] actual = new SalesmanParser().parse(data);
		String[] expected = new String[] {"001","3245678865434","Paulo", "40000.99"};
		Assert.assertArrayEquals(expected, actual);
	}

	@Test(expected = ParseException.class)
	public void testParseWithWrongType() {
		String data = "002ç3244448865434çPauloç434";
		new SalesmanParser().parse(data);
	}

	public void testParseNameWithCedilla() {
		String data = "001ç3222678865434çPaulo Golçalvesç12000.90";
		String[] actual = new SalesmanParser().parse(data);
		String[] expected = new String[] {"001","3222678865434","Paulo Golçalves", "12000.90"};
		Assert.assertArrayEquals(expected, actual);
	}

}
