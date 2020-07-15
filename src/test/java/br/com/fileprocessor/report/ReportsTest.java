package br.com.fileprocessor.report;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.service.FileProcessor;
import br.com.fileprocessor.service.SalesData;

public class ReportsTest {

    private final String INPUT_FILENAME = "test02.dat";
    private SalesData salesData;

    @Before
    public void before() throws IOException {
        File file = getFileFromResources(INPUT_FILENAME);
        this.salesData = new FileProcessor().buildSalesData(file);
    }

    @Test
    public void testGetNumberOfCustomers() {
        int number = Reports.getNumberOfCustomers(salesData);
        Assert.assertEquals(3, number);
    }

    @Test
    public void testGetNumberOfSalesmen() {
        int number = Reports.getNumberOfSalesmen(salesData);
        Assert.assertEquals(4, number);
    }

    @Test
    public void testGetWorstSalesman() {
        Collection<String> worst = Reports.getWorstSalesman(salesData);
        boolean equals = CollectionUtils.isEqualCollection(Arrays.asList("Pior Vendedor"), worst);
        Assert.assertTrue(equals);
    }

    @Test
    public void testGetAllSalesmanNames() {
        Collection<String> actual = Reports.getAllSalesmanNames(salesData);
        Collection<String> expected = Arrays.asList("Pedro", "Paulo Gonçalves", "João", "Pior Vendedor");
        boolean equals = CollectionUtils.isEqualCollection(expected, actual);
        Assert.assertTrue(equals);
    }

    @Test
    public void testGetAllSalesmanNamesWithSales() {
        Collection<String> actual = Reports.getAllSalesmanNamesWithSales(salesData);
        Collection<String> expected = Arrays.asList("Pedro", "Paulo Gonçalves", "João");
        boolean equals = CollectionUtils.isEqualCollection(expected, actual);
        Assert.assertTrue(equals);
    }

    @Test
    public void testGetMostExpensiveSale() {
        Sale sale = Reports.getMostExpensiveSale(salesData); // What if there are more than one with same amount?
        Assert.assertEquals("04", sale.getSaleId());
        Assert.assertEquals(new BigDecimal("2009.9").compareTo(sale.getTotalPrice()), 0);
        Assert.assertEquals("João", sale.getSalesmanName());
    }

    private File getFileFromResources(String file) {
        return new File(getClass().getClassLoader().getResource(file).getFile());
    }

}
