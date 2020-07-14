package br.com.fileprocessor.model;

import java.math.BigDecimal;
import java.util.Collections;
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

    public Sale() {
        // Empty constructor
    }

    /**
     * Creates a new Sale instance.
     *
     * @param saleId the identifier of the sale
     * @param saleItems the purchased items
     * @param salesmanName the salesman name
     */
    public Sale(String saleId, List<SaleItem> saleItems, String salesmanName) {
        this.saleId = saleId;
        this.saleItems = saleItems;
        this.salesmanName = salesmanName;
    }

    /**
     * Private constructor for {@link Builder} purposes.
     *
     * @param builder the Sale {@link Builder}
     */
    private Sale(Builder builder) {
        this.saleId = builder.saleId;
        this.saleItems = builder.saleItems;
        this.salesmanName = builder.salesmanName;
    }

    /**
     * Creates builder to build {@link Sale}.
     *
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link Sale}.
     */
    public static final class Builder {
        private String saleId;
        private List<SaleItem> saleItems = Collections.emptyList();
        private String salesmanName;

        private Builder() {
        }

        public Builder withSaleId(String saleId) {
            this.saleId = saleId;
            return this;
        }

        public Builder withSaleItems(List<SaleItem> saleItems) {
            this.saleItems = saleItems;
            return this;
        }

        public Builder withSalesmanName(String salesmanName) {
            this.salesmanName = salesmanName;
            return this;
        }

        public Sale build() {
            return new Sale(this);
        }
    }

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
        return Objects.hash(saleId);
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
        return Objects.equals(saleId, other.saleId);
    }

    @Override
    public String toString() {
        return String.format("Sale [saleId=%s, saleItems=%s, salesmanName=%s]", saleId, saleItems, salesmanName);
    }
}
