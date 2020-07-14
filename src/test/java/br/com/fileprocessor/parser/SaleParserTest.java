package br.com.fileprocessor.parser;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.exception.ParseException;

public class SaleParserTest {

    @Test
    public void testParse() {
        String data = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        String[] actual = new SaleParser().parse(data);
        String[] expected = new String[] {"003","10","[1-10-100,2-30-2.50,3-40-3.10]", "Pedro"};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = ParseException.class)
    public void testParseWithWrongType() {
        String data = "004ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        new CustomerParser().parse(data);
    }

    public void testParseNameWithCedilla() {
        String data = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo Gonçalves";
        String[] actual = new CustomerParser().parse(data);
        String[] expected = new String[] {"003","08","[1-34-10,2-33-1.50,3-40-0.10]", "Paulo Gonçalves"};
        Assert.assertArrayEquals(expected, actual);
    }

}
