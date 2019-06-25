package com.hcl.ox.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.entity.Officer;
import com.hcl.ox.repository.CustomerRepo;
import com.hcl.ox.repository.LoanRepo;
import com.hcl.ox.service.CustomerLoanService;

@Service
public class CustomerLoanServiceImpl implements CustomerLoanService {

	@Autowired
	LoanRepo loanRepo;
	
	@Autowired
	CustomerRepo customerRepo;

	@Override
	public List<Customer> loanRejections(long id) {

		Officer Officer = new Officer();
		Officer.setId(id);
		Loan loan = loanRepo.findByOfficer(Officer);
		if (loan != null) {
			List<Customer> li = new ArrayList<>();
			Customer customer= new Customer();
			if (loan.getLoanStatus().equals("Rejected"))
				customer=customerRepo.findBycustomerId(loan.getCustomer().getCustomerId());
				li.add(customer);
			return  li;
		}
		return null;
	
	}

	@Override
	public List<Customer> loanApproved(long id) {
		Officer Officer = new Officer();
		Officer.setId(id);
		Loan loan = loanRepo.findByOfficer(Officer);
		if (loan != null) {
			List<Customer> li = new ArrayList<>();
			Customer customer= new Customer();
			if (loan.getLoanStatus().equals("Approved"))
				customer=customerRepo.findBycustomerId(loan.getCustomer().getCustomerId());
				li.add(customer);
			return  li;
		}
		return null;
	}

}
