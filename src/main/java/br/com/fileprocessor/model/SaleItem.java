package br.com.fileprocessor.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The model that represents the purchased item.
 */
public class SaleItem {

    /**
     * The purchased item identifier.
     */
    private Integer itemId;

    /**
     * The purchased item quantity.
     */
    private Integer itemQuantity;

    /**
     * The item unit price.
     */
    private BigDecimal itemPrice;

    public SaleItem() {
        // Empty constructor
    }

    /**
     * Creates a new SaleItem instance.
     *
     * @param itemId the purchased item identifier
     * @param itemQuantity the purchased item quantity
     * @param itemPrice The item unit price
     */
    public SaleItem(Integer itemId, Integer itemQuantity, BigDecimal itemPrice) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    private SaleItem(Builder builder) {
        this.itemId = builder.itemId;
        this.itemQuantity = builder.itemQuantity;
        this.itemPrice = builder.itemPrice;
    }

    /**
     * Creates builder to build {@link SaleItem}.
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link SaleItem}.
     */
    public static final class Builder {
        private Integer itemId;
        private Integer itemQuantity;
        private BigDecimal itemPrice;

        private Builder() {
        }

        public Builder withItemId(Integer itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder withItemQuantity(Integer itemQuantity) {
            this.itemQuantity = itemQuantity;
            return this;
        }

        public Builder withItemPrice(BigDecimal itemPrice) {
            this.itemPrice = itemPrice;
            return this;
        }

        public SaleItem build() {
            return new SaleItem(this);
        }
    }

    /**
     * @return {@link #itemId}
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId {@link #itemId}
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return {@link #itemQuantity}
     */
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    /**
     * @param itemQuantity {@link #itemQuantity}
     */
    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * @return {@link #itemPrice}
     */
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * @param itemPrice {@link #itemPrice}
     */
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Get the total price of the sale item (unit price * quantity).
     *
     * @return the total price of the sale item
     */
    public BigDecimal getTotalPrice() {
        return itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemPrice, itemQuantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SaleItem other = (SaleItem) obj;
        return Objects.equals(itemId, other.itemId)
                && Objects.equals(itemPrice, other.itemPrice)
                && Objects.equals(itemQuantity, other.itemQuantity);
    }

    @Override
    public String toString() {
        return String.format("SaleItem [itemId=%s, itemQuantity=%s, itemPrice=%s]", itemId, itemQuantity, itemPrice);
    }

}
