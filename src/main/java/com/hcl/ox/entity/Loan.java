package com.hcl.ox.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loanDetails")

public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loanAccountNumber;
	private String securityAddress;
	private double securityAmount;
	private String loanStatus;
	private double loanAmount;

	@OneToOne
	@JoinColumn(name = "CustomerId")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "Id")
	private Officer officer;

	public long getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(long loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public String getSecurityAddress() {
		return securityAddress;
	}

	public void setSecurityAddress(String securityAddress) {
		this.securityAddress = securityAddress;
	}

	public double getSecurityAmount() {
		return securityAmount;
	}

	public void setSecurityAmount(double securityAmount) {
		this.securityAmount = securityAmount;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

}
