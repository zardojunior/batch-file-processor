package br.com.fileprocessor.factory;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.exception.ParserNotFoundException;
import br.com.fileprocessor.parser.CustomerParser;
import br.com.fileprocessor.parser.SaleParser;
import br.com.fileprocessor.parser.SalesmanParser;

public class ParserFactoryTest {

    @Test
    public void testCreate() {
        Assert.assertTrue(ParserFactory.create("001") instanceof SalesmanParser);
        Assert.assertTrue(ParserFactory.create("002") instanceof CustomerParser);
        Assert.assertTrue(ParserFactory.create("003") instanceof SaleParser);
        Assert.assertNull(ParserFactory.create("XXX"));
    }

    @Test(expected = ParserNotFoundException.class)
    public void testCreateThrowsException() {
        ParserFactory.createThrowsException("XXX");
    }

}
