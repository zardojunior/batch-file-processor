package br.com.fileprocessor.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.model.Sale;

public class SaleConverterTest {

    @Test
    public void testConvert() {
        String[] data = new String[] {"003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro"};
        Sale sale = new SaleConverter().convert(data);
        Assert.assertEquals("10", sale.getSaleId());
        Assert.assertEquals("Pedro", sale.getSalesmanName());

        Assert.assertEquals(3, sale.getSaleItems().size());

        Assert.assertEquals(new Integer("1"), sale.getSaleItems().get(0).getItemId());
        Assert.assertEquals(new Integer("10"), sale.getSaleItems().get(0).getItemQuantity());
        Assert.assertTrue(new BigDecimal("100").compareTo(sale.getSaleItems().get(0).getItemPrice()) == 0);
        Assert.assertTrue(new BigDecimal("1000").compareTo(sale.getSaleItems().get(0).getTotalPrice()) == 0);

        Assert.assertEquals(new Integer("2"), sale.getSaleItems().get(1).getItemId());
        Assert.assertEquals(new Integer("30"), sale.getSaleItems().get(1).getItemQuantity());
        Assert.assertTrue(new BigDecimal("2.50").compareTo(sale.getSaleItems().get(1).getItemPrice()) == 0);
        Assert.assertTrue(new BigDecimal("75").compareTo(sale.getSaleItems().get(1).getTotalPrice()) == 0);

        Assert.assertEquals(new Integer("3"), sale.getSaleItems().get(2).getItemId());
        Assert.assertEquals(new Integer("40"), sale.getSaleItems().get(2).getItemQuantity());
        Assert.assertTrue(new BigDecimal("3.10").compareTo(sale.getSaleItems().get(2).getItemPrice()) == 0);
        Assert.assertTrue(new BigDecimal("124").compareTo(sale.getSaleItems().get(2).getTotalPrice()) == 0);

        Assert.assertTrue(new BigDecimal("1199").compareTo(sale.getTotalPrice()) == 0);
    }

}
