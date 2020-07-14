package br.com.fileprocessor.factory;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.converter.CustomerConverter;
import br.com.fileprocessor.converter.SaleConverter;
import br.com.fileprocessor.converter.SalesmanConverter;

public class ConverterFactoryTest {

    @Test
    public void testCreate() {
        Assert.assertTrue(ConverterFactory.create("001") instanceof SalesmanConverter);
        Assert.assertTrue(ConverterFactory.create("002") instanceof CustomerConverter);
        Assert.assertTrue(ConverterFactory.create("003") instanceof SaleConverter);
        Assert.assertNull(ConverterFactory.create("004"));
    }

}
