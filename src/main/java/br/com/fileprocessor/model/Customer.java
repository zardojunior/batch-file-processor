package br.com.fileprocessor.model;

import java.util.Objects;

/**
 * The customer model.
 */
public class Customer implements Model {

	public static final String TYPE = "002";

	public Customer() {
		// Empty constructor
	}

	/**
	 * Creates a new Customer instance.
	 *
	 * @param cnpj the customer CNPJ
	 * @param name the customer name
	 * @param businessArea the customer business area
	 */
	public Customer(String cnpj, String name, String businessArea) {
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;
	}

	/**
	 * The customer CNPJ.
	 */
	private String cnpj;

	/**
	 * The customer name.
	 */
	private String name;

	/**
	 * The customer business area.
	 */
	private String businessArea;

	private Customer(Builder builder) {
		this.cnpj = builder.cnpj;
		this.name = builder.name;
		this.businessArea = builder.businessArea;
	}

	/**
	 * @return {@link #cnpj}
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj {@link #cnpj}
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name {@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return {@link #businessArea}
	 */
	public String getBusinessArea() {
		return businessArea;
	}

	/**
	 * @param businessArea {@link #businessArea}
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(cnpj, other.cnpj);
	}

	/**
	 * Creates builder to build {@link Customer}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Customer}.
	 */
	public static final class Builder {
		private String cnpj;
		private String name;
		private String businessArea;

		private Builder() {
		}

		public Builder withCnpj(String cnpj) {
			this.cnpj = cnpj;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withBusinessArea(String businessArea) {
			this.businessArea = businessArea;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}

	@Override
	public String toString() {
		return String.format("Customer [cnpj=%s, name=%s, businessArea=%s]", cnpj, name, businessArea);
	}
}
