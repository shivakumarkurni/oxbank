package com.hcl.ox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ox.entity.Customer;
import com.hcl.ox.service.CustomerLoanService;

@RestController
@RequestMapping("/")
public class CustomerLoanController {

	@Autowired
	CustomerLoanService customerLoanService;

	@GetMapping("/rejectedcustomers/{id}")
	public List<Customer> loanRejections(@PathVariable long id) throws Exception {
		List<Customer> li = customerLoanService.loanRejections(id);
		if (li != null && !li.isEmpty()) {
			return li;
		} else
			throw new Exception("no data present");
	}

	@GetMapping("/approvedcustomers/{id}")
	public List<Customer> loanApproved(@PathVariable long id) throws Exception {
		List<Customer> li = customerLoanService.loanApproved(id);
		if (li != null && !li.isEmpty()) {
			return li; 
		} else
			throw new Exception("no data present");
	}
}
