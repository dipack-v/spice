package com.spice.dao.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BankEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String branch;
	private String city;
	private String country;
	private String routingCode;
	private String accountNumber;
	private String swift;
	private String iban;
	private String currency;

	public BankEntity() {
		super();
	}

	public BankEntity(Long id, String name, String branch, String city, String country, String routingCode, String accountNumber, String swift,
			String iban, String currency) {
		super();
		this.id = id;
		this.name = name;
		this.branch = branch;
		this.city = city;
		this.country = country;
		this.routingCode = routingCode;
		this.accountNumber = accountNumber;
		this.swift = swift;
		this.iban = iban;
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRoutingCode() {
		return routingCode;
	}

	public void setRoutingCode(String routingCode) {
		this.routingCode = routingCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((iban == null) ? 0 : iban.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((routingCode == null) ? 0 : routingCode.hashCode());
		result = prime * result + ((swift == null) ? 0 : swift.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankEntity other = (BankEntity) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (iban == null) {
			if (other.iban != null)
				return false;
		} else if (!iban.equals(other.iban))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (routingCode == null) {
			if (other.routingCode != null)
				return false;
		} else if (!routingCode.equals(other.routingCode))
			return false;
		if (swift == null) {
			if (other.swift != null)
				return false;
		} else if (!swift.equals(other.swift))
			return false;
		return true;
	}
	
	

}
