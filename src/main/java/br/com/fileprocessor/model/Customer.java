package br.com.fileprocessor.model;

import java.util.Objects;

/**
 * The customer model.
 */
public class Customer implements Model {

	public static final String TYPE = "002";

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

	@Override
	public String toString() {
		return String.format("Customer [cnpj=%s, name=%s, businessArea=%s]", cnpj, name, businessArea);
	}
}
