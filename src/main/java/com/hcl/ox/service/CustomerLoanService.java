package com.hcl.ox.service;

import java.util.List;

import com.hcl.ox.entity.Customer;

public interface CustomerLoanService {
	
	public List<Customer> loanRejections(long id);
	
	public List<Customer> loanApproved(long id);

}
