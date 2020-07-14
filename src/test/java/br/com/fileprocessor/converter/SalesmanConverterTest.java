package br.com.fileprocessor.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.exception.ConversionException;
import br.com.fileprocessor.model.Salesman;

public class SalesmanConverterTest {

    @Test
    public void testConvert() {
        String[] data = new String[] {"001","3245678865434","Paulo", "40000.99"};
        Salesman salesman = new SalesmanConverter().convert(data);
        Assert.assertEquals("3245678865434", salesman.getCpf());
        Assert.assertEquals("Paulo", salesman.getName());
        Assert.assertEquals(new BigDecimal(data[3]), salesman.getSalary());
    }

    @Test(expected = ConversionException.class)
    public void testConvertWrongSalaryFormat() {
        String[] data = new String[] {"001","3245678865434","Paulo", "$1000"};
        new SalesmanConverter().convert(data);
    }

}
