package com.hcl.ox.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ox.entity.Customer;
import com.hcl.ox.entity.Loan;
import com.hcl.ox.repository.CustomerRepo;
import com.hcl.ox.repository.LoanRepo;
import com.hcl.ox.service.OfficerDetailsService;

@Service
public class OfficerDetailsServiceImpl implements OfficerDetailsService {

	@Autowired
	LoanRepo loanRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Override
	public String approvingLoan(long customerId) {
		Loan loan = new Loan();
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		loan = loanRepo.findByCustomer(customer);
		if (loan != null) {
			loan = loanRepo.findByloanAccountNumber(loan.getLoanAccountNumber());
			if (loan != null) {
				return "customer is already having loan";
			} else if (loan == null) {
				customer = customerRepo.findBycustomerId(customerId);
				if (customer.getCreditScore() >= 600 && (customer.getAge() >= 24 || customer.getAge() <= 60)) {
					loan.setLoanStatus("Aprooved");
					loanRepo.save(loan);
					return "Aprooved loan";
				}

			}
		} else if (loan == null) {
			return "he can apply for the loan or he is not having loanaccount";
		}

		return null;
	}

}
