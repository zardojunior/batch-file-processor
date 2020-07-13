package br.com.fileprocessor.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.fileprocessor.exception.ConversionException;
import br.com.fileprocessor.model.Model;
import br.com.fileprocessor.model.Sale;
import br.com.fileprocessor.model.SaleItem;

public class SaleConverter implements Converter<String[], Model> {

	@Override
	public Sale convert(String[] data) {
		try {
			return Sale.builder()
					.withSaleId(data[1])
					.withSaleItems(parseSaleItems(data[2]))
					.withSalesmanName(data[3])
					.build();
		} catch (Exception e) {
			String errorMessage = String.format("Could not convert the data %s", Arrays.asList(data));
			throw new ConversionException(errorMessage, e);
		}
	}

	/**
	 * Parse a string with format
	 * <pre>003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name</pre>
	 * and convert into a SaleItem instance.
	 *
	 * <p>Example:
	 * <pre>003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro</pre>
	 */
	private List<SaleItem> parseSaleItems(String text) {
		if (text == null || text.isEmpty() || !text.startsWith("[") || !text.endsWith("]")) {
			return Collections.emptyList();
		}
		String innerText = text.substring(1, text.length() -1);
		if (innerText.isEmpty()) {
			return Collections.emptyList();
		}
		String[] splitted = innerText.split(",");
		List<SaleItem> saleItems = new ArrayList<>(splitted.length);
		for (String string : splitted) {
			String[] values = string.split("-");
			SaleItem saleItem = SaleItem.builder()
					.withItemId(Integer.parseInt(values[0]))
					.withItemQuantity(Integer.parseInt(values[1]))
					.withItemPrice(new BigDecimal(values[2]))
					.build();
			saleItems.add(saleItem);
		}
		return saleItems;
	}

}
