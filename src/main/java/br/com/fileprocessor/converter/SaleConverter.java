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
			Sale sale = new Sale();
			sale.setSaleId(data[1]);
			sale.setSaleItems(parseSaleItems(data[2]));
			sale.setSalesmanName(data[3]);
			return sale;
		} catch (Exception e) {
			String errorMessage = String.format("Could not convert the data %s", Arrays.asList(data));
			throw new ConversionException(errorMessage, e);
		}
	}

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
			SaleItem saleItem = new SaleItem();
			saleItem.setItemId(Integer.parseInt(values[0]));
			saleItem.setItemQuantity(Integer.parseInt(values[1]));
			saleItem.setItemPrice(new BigDecimal(values[2]));
			saleItems.add(saleItem);
		}
		return saleItems;
	}

}
