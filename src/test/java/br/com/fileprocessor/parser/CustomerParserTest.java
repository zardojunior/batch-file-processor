package br.com.fileprocessor.parser;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.exception.ParseException;

public class CustomerParserTest {

	@Test
	public void testParse() {
		String data = "002ç2345675433444345çEduardo PereiraçRural";
		String[] actual = new CustomerParser().parse(data);
		String[] expected = new String[] {"002","2345675433444345","Eduardo Pereira", "Rural"};
		Assert.assertArrayEquals(expected, actual);
	}

	@Test(expected = ParseException.class)
	public void testParseWithWrongType() {
		String data = "001ç2345675433444345çEduardo PereiraçRural";
		new CustomerParser().parse(data);
	}

	public void testParseNameWithCedilla() {
		String data = "002ç2311175433444345çEduardo Gonçalves PereiraçRural";
		String[] actual = new CustomerParser().parse(data);
		String[] expected = new String[] {"002","2311175433444345","Eduardo Gonçalves Pereira", "Rural"};
		Assert.assertArrayEquals(expected, actual);
	}

}
