package br.com.fileprocessor.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The salesman model.
 */
public class Salesman implements Model {

	public static final String TYPE = "001";

	/**
	 * The salesman CPF.
	 */
	private String cpf;

	/**
	 * The salesman name.
	 */
	private String name;

	/**
	 * The salesman salary.
	 */
	private BigDecimal salary;

	/**
	 * @return {@link #cpf}
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf {@link #cpf}
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	 * @return {@link #salary}
	 */
	public BigDecimal getSalary() {
		return salary;
	}

	/**
	 * @param salary {@link #salary}
	 */
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salesman other = (Salesman) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return String.format("Salesman [cpf=%s, name=%s, salary=%s]", cpf, name, salary);
	}

}
