package br.com.fileprocessor.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The salesman model.
 */
public class Salesman implements Model {

    public static final String TYPE = "001";

    public Salesman() {
        // Empty constructor
    }

    /**
     * Creates a new Salesman instance.
     *
     * @param cpf the salesman CPF
     * @param name the salesman name
     * @param salary the salesman salary
     */
    public Salesman(String cpf, String name, BigDecimal salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    /**
     * Private constructor for {@link Builder} purposes.
     *
     * @param builder the Salesman {@link Builder}
     */
    private Salesman(Builder builder) {
        this.cpf = builder.cpf;
        this.name = builder.name;
        this.salary = builder.salary;
    }

    /**
     * Creates builder to build {@link Salesman}.
     *
     * @return created builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link Salesman}.
     */
    public static final class Builder {
        private String cpf;
        private String name;
        private BigDecimal salary;

        private Builder() {
        }

        public Builder withCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSalary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Salesman build() {
            return new Salesman(this);
        }
    }

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
