package br.com.fileprocessor.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * The sale model.
 */
public class Sale implements Model {

	public static final String TYPE = "003";

	/**
	 * The identifier of the sale.
	 */
	private String saleId;

	/**
	 * The purchased items.
	 */
	private List<SaleItem> saleItems;

	/**
	 * The salesman name.
	 */
	private String salesmanName;

	/**
	 * @return {@link #saleId}
	 */
	public String getSaleId() {
		return saleId;
	}

	/**
	 * @param saleId {@link #saleId}
	 */
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	/**
	 * @return {@link #saleItems}
	 */
	public List<SaleItem> getSaleItems() {
		return saleItems;
	}

	/**
	 * @param saleItems {@link #saleItems}
	 */
	public void setSaleItems(List<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}

	/**
	 * @return {@link #salesmanName}
	 */
	public String getSalesmanName() {
		return salesmanName;
	}

	/**
	 * @param salesmanName {@link #salesmanName}
	 */
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	/**
	 * Get the total price of the purchase.
	 *
	 * @return the total price of the purchase
	 */
	public BigDecimal getTotalPrice() {
		return saleItems.stream()
				.map(SaleItem::getTotalPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(saleId, saleItems, salesmanName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		return Objects.equals(saleId, other.saleId)
				&& Objects.equals(saleItems, other.saleItems)
				&& Objects.equals(salesmanName, other.salesmanName);
	}

	@Override
	public String toString() {
		return String.format("Sale [saleId=%s, saleItems=%s, salesmanName=%s]", saleId, saleItems, salesmanName);
	}

}
