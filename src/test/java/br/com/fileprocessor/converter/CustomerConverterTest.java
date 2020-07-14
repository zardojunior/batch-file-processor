package br.com.fileprocessor.converter;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.model.Customer;

public class CustomerConverterTest {

    @Test
    public void testConvert() {
        String[] data = new String[] {"002","2345675433444345","Eduardo Pereira", "Rural"};
        Customer customer = new CustomerConverter().convert(data);
        Assert.assertEquals("2345675433444345", customer.getCnpj());
        Assert.assertEquals("Eduardo Pereira", customer.getName());
        Assert.assertEquals("Rural", customer.getBusinessArea());
    }

}
