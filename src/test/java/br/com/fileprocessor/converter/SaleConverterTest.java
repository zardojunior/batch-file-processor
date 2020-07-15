package br.com.fileprocessor.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.model.SaleItem;

public class SaleConverterTest {

    @Test
    public void testConvert() {
        String[] data = new String[] {"003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro Gonçalves"};
        Sale sale = new SaleConverter().convert(data);
        Assert.assertEquals("10", sale.getSaleId());
        Assert.assertEquals("Pedro Gonçalves", sale.getSalesmanName());

        Assert.assertEquals(3, sale.getSaleItems().size());

        SaleItem saleItem0 = sale.getSaleItems().get(0);
        Assert.assertEquals(new Integer("1"), saleItem0.getItemId());
        Assert.assertEquals(new Integer("10"), saleItem0.getItemQuantity());
        Assert.assertTrue(equalsBigDecimal("100", saleItem0.getItemPrice()));
        Assert.assertTrue(equalsBigDecimal("1000", saleItem0.getTotalPrice()));

        SaleItem saleItem1 = sale.getSaleItems().get(1);
        Assert.assertEquals(new Integer("2"), saleItem1.getItemId());
        Assert.assertEquals(new Integer("30"), saleItem1.getItemQuantity());
        Assert.assertTrue(equalsBigDecimal("2.50", saleItem1.getItemPrice()));
        Assert.assertTrue(equalsBigDecimal("75", saleItem1.getTotalPrice()));

        SaleItem saleItem2 = sale.getSaleItems().get(2);
        Assert.assertEquals(new Integer("3"), saleItem2.getItemId());
        Assert.assertEquals(new Integer("40"), saleItem2.getItemQuantity());
        Assert.assertTrue(equalsBigDecimal("3.10", saleItem2.getItemPrice()));
        Assert.assertTrue(equalsBigDecimal("124", saleItem2.getTotalPrice()));

        // Sale total price
        Assert.assertTrue(equalsBigDecimal("1199", sale.getTotalPrice()));
    }

    private boolean equalsBigDecimal(String expected, BigDecimal actual) {
        return new BigDecimal(expected).compareTo(actual) == 0;
    }

}
